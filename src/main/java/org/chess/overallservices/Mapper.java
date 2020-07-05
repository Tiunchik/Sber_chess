package org.chess.overallservices;

import org.chess.game.ChessGame;
import org.chess.game.ChessGameDTO;
import org.chess.player.ChessPlayer;
import org.chess.player.ChessPlayerDTO;
import org.chess.school.ChessSchool;
import org.chess.school.ChessSchoolDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Scope("singleton")
public class Mapper {

    public ChessPlayerDTO convertToPlayerDTO(ChessPlayer plr) {
        ChessPlayerDTO dto = new ChessPlayerDTO();
        dto.id = plr.getId();
        dto.name = plr.getName();
        dto.elo = plr.getElo();
        dto.school = converToSchoolDTO(plr.getSchool());
        return dto;
    }

    public ChessSchoolDTO converToSchoolDTO(ChessSchool schl) {
        ChessSchoolDTO dto = new ChessSchoolDTO();
        dto.id = schl.getId();
        dto.called = schl.getCalled();
        return dto;
    }

    public ChessGameDTO convertToGameDTO(ChessGame game) {
        ChessGameDTO dto = new ChessGameDTO();
        dto.id = game.getId();
        dto.firstplayer = convertToPlayerDTO(game.getFirstPlayer());
        dto.secondplayer = convertToPlayerDTO(game.getSecondPlayer());
        dto.gameFinished = game.getGameFinished().getTime();
        dto.firstPlayerEloChanged = game.getFirstPlayerEloChanged();
        dto.secondPlayerEloChanged = game.getSecondPlayerEloChanged();
        return dto;
    }

    public ChessPlayer convertToPlayer(ChessPlayerDTO dto) {
        ChessPlayer plr = new ChessPlayer(dto.id);
        plr.setName(dto.name);
        plr.setElo(dto.elo);
        plr.setSchool(convertToSchool(dto.school));
        return plr;
    }

    public ChessSchool convertToSchool(ChessSchoolDTO dto) {
        ChessSchool schl = new ChessSchool(dto.id);
        schl.setCalled(dto.called);
        return schl;
    }

    public ChessGame convertToGame(ChessGameDTO dto) {
        ChessGame game = new ChessGame(dto.id);
        game.setFirstPlayer(convertToPlayer(dto.firstplayer));
        game.setSecondPlayer(convertToPlayer(dto.secondplayer));
        game.setGameFinished(new Timestamp(dto.gameFinished));
        game.setFirstPlayerEloChanged(dto.firstPlayerEloChanged);
        game.setSecondPlayerEloChanged(dto.secondPlayerEloChanged);
        return game;
    }
}
