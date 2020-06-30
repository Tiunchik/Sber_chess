package org.chess.logic;

import org.chess.domains.ChessGame;
import org.chess.domains.ChessPlayer;
import org.chess.repositories.GameRepository;
import org.chess.repositories.PlayerRepository;
import org.chess.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

//Ea = 1 / (1 + 10*((Rb-Ra)/400)) - шанс выйгрыша первого игрока
//Eb = 1 / (1 + 10*((Ra-Rb)/400)) - шанс выйгрыша второго игрока
//Ra - рейтинг игрока 1
//Rb - рейтинг игрока 2
//K = 16
//S = 1 победа, 0 проигрышь, ничью в расчёт не брал

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

    private static final double K = 16.0;

    @Scheduled(fixedRate = 30000L)
    public void calculate() {
        if (loadPlayers()) {
            double sa, sb;
            double ra = first.getElo(), rb = second.getElo();
            double ea = 1 / (1 + 10 * ((rb - ra) / 400));
            double eb = 1 / (1 + 10 * ((ra - rb) / 400));
            if (howWin()) {
                sa = 1;
                sb = 0;
            } else {
                sa = 0;
                sb = 1;
            }
            int newFirst = (int) (K * (sa - ea));
            int newSecond = (int) (K * (sb - eb));
            if (saveGame(sa, newFirst, newSecond).getId() != 0) {
                plrRep.changeElo(first.getId(), newFirst);
                plrRep.changeElo(second.getId(), newSecond);
            }
        }
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

    private boolean howWin() {
        return rand.nextInt(1) == 0;
    }

    private ChessGame saveGame(double sa, int newFirst, int newSecond) {
        Timestamp finish = Timestamp.from(Instant.now());
        ChessGame game = new ChessGame();
        game.setFirstPlayer(first);
        game.setSecondPlayer(second);
        game.setWinner(sa == 1 ? first : second);
        game.setGameFinished(finish);
        game.setFirstPlayerEloChanged(newFirst);
        game.setSecondPlayerEloChanged(newSecond);
        game = gamRep.save(game);
        return game;
    }
}
