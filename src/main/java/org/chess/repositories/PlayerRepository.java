package org.chess.repositories;

import org.chess.domains.ChessPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlayerRepository extends JpaRepository<ChessPlayer, Integer> {

    @Query(value = "select * from player order by random() limit 1",
            nativeQuery = true)
    ChessPlayer getFirstPlayer();

    @Query(value="select * from player where school_id = ?1 order by random() limit 1",
            nativeQuery = true)
    ChessPlayer getSecondPlayer(int school_id);

    @Modifying
    @Transactional
    @Query(value = "update player set elo = elo + ?2 where id = ?1",
    nativeQuery = true)
    int changeElo(int player_id, int elochange);

}
