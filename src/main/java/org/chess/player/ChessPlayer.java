package org.chess.player;

import org.chess.school.ChessSchool;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "player")
public class ChessPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int elo;

    @ManyToOne
    @JoinColumn(name = "school_id",
            foreignKey = @ForeignKey(name = "SCHOOL_ID_FK")
    )
    private ChessSchool school;

    public ChessPlayer() {
    }

    public ChessPlayer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public ChessSchool getSchool() {
        return school;
    }

    public void setSchool(ChessSchool school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPlayer that = (ChessPlayer) o;
        return id == that.id
                && elo == that.elo
                && name.equals(that.name)
                && school.equals(that.school);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, elo, school.getCalled());
    }

    @Override
    public String toString() {
        return "ChessPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", elo=" + elo +
                ", school=" + school.getCalled() +
                '}';
    }
}
