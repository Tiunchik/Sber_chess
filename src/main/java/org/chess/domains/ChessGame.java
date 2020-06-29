package org.chess.domains;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name="game")
public class ChessGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "firstplayer_id",
            foreignKey = @ForeignKey(name = "FIRST_PLAYER_ID_FK")
    )
    private ChessPlayer firstPlayer;

    @ManyToOne
    @JoinColumn(name = "secondplayer_id",
            foreignKey = @ForeignKey(name = "SECOND_PLAYER_ID_FK")
    )
    private ChessPlayer secondPlayer;

    @CreationTimestamp
    private Timestamp gameFinished;

    @ManyToOne
    @JoinColumn(name = "winner_id",
            foreignKey = @ForeignKey(name = "WINNER_ID_FK")
    )
    private ChessPlayer winner;

    @Column(nullable = false)
    private int firstPlayerEloChanged;

    @Column(nullable = false)
    private int secondPlayerEloChanged;

    public ChessGame() {
    }

    public ChessGame(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public ChessPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(ChessPlayer firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public ChessPlayer getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(ChessPlayer secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Timestamp getGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(Timestamp gameFinished) {
        this.gameFinished = gameFinished;
    }

    public ChessPlayer getWinner() {
        return winner;
    }

    public void setWinner(ChessPlayer winner) {
        this.winner = winner;
    }

    public int getFirstPlayerEloChanged() {
        return firstPlayerEloChanged;
    }

    public void setFirstPlayerEloChanged(int firstPlayerEloChanged) {
        this.firstPlayerEloChanged = firstPlayerEloChanged;
    }

    public int getSecondPlayerEloChanged() {
        return secondPlayerEloChanged;
    }

    public void setSecondPlayerEloChanged(int secondPlayerEloChanged) {
        this.secondPlayerEloChanged = secondPlayerEloChanged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return id == chessGame.id
                && firstPlayer.equals(chessGame.firstPlayer)
                && secondPlayer.equals(chessGame.secondPlayer)
                && gameFinished.equals(chessGame.gameFinished)
                && winner.equals(chessGame.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstPlayer.getName(), secondPlayer.getName(), gameFinished, winner.getName());
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "id=" + id +
                ", firstPlayer=" + firstPlayer.getName() +
                ", secondPlayer=" + secondPlayer.getName() +
                ", gameFinished=" + gameFinished +
                ", winner=" + winner.getName() +
                '}';
    }
}
