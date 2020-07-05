package org.chess.game;

import org.chess.player.ChessPlayerDTO;

public class ChessGameDTO {

    public long id;

    public ChessPlayerDTO firstplayer;

    public ChessPlayerDTO secondplayer;

    public long gameFinished;

    public int firstPlayerEloChanged;

    public int secondPlayerEloChanged;
}
