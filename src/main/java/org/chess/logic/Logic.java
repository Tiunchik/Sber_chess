package org.chess.logic;

import org.library.annotations.Monitoring;
import org.chess.domains.ChessGame;
import org.chess.domains.ChessPlayer;
import org.chess.repositories.GameRepository;
import org.chess.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

@Component
@Scope(value = "singleton")
public class Logic {

    private final GameRepository gamRep;

    private final PlayerRepository plrRep;

    private final Random rand = new Random();

    public Logic(@Autowired GameRepository gamRep,
                 @Autowired PlayerRepository plrRep) {
        this.gamRep = gamRep;
        this.plrRep = plrRep;
    }

    private ChessPlayer first;

    private ChessPlayer second;

    @Scheduled(fixedRate = 30000L)
    @Monitoring (name = "DO_CALCULATE")
    public void calculate() {
        if (loadPlayers()) {
            double sa, sb;
            double ra = first.getElo(), rb = second.getElo();
            double ea = 1 / (1 + Math.pow(10, (rb - ra) / 400));
            double eb = 1 / (1 + Math.pow(10, (ra - rb) / 400));
            int result = rand.nextInt(2);
            sa = result == 0 ? 1 : (result == 2 ? 0.5 : 0);
            sb = result == 1 ? 1 : (result == 2 ? 0.5 : 0);
            int eloFirst = (int) (calculateKRatingForPlayer(first) * (sa - ea));
            int eloSecond = (int) (calculateKRatingForPlayer(second) * (sb - eb));
            if (saveGame(sa, eloFirst, eloSecond).getId() != 0) {
                plrRep.changeElo(first.getId(), eloFirst);
                plrRep.changeElo(second.getId(), eloSecond);
            }
        }
    }

    private double calculateKRatingForPlayer(ChessPlayer player) {
        int gamesQuantity = gamRep.howManyGames(player.getId());
        return gamesQuantity <= 30 ? 30.0 : (player.getElo() < 1600 ? 15.0 : 10);
    }

    private boolean loadPlayers() {
        first = null;
        second = null;
        first = plrRep.getFirstPlayer();
        if (first == null) {
            return false;
        }
        second = plrRep.getSecondPlayer(first.getSchool().getId());
        return second != null;
    }

    private ChessGame saveGame(double sa, int eloFirst, int eloSecond) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        ChessGame game = new ChessGame(cal.getTimeInMillis());
        game.setFirstPlayer(sa == 1 ? first : sa == 0 ? second : first);
        game.setSecondPlayer(sa == 1 ? second : sa == 0 ? first : second);
        game.setFirstPlayerEloChanged(sa == 1 ? eloFirst : sa == 0 ? eloSecond : eloFirst);
        game.setSecondPlayerEloChanged(sa == 1 ? eloSecond : sa == 0 ? eloFirst : eloSecond);
        game = gamRep.save(game);
        return game;
    }
}
