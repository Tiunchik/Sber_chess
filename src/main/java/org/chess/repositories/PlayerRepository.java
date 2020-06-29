package org.chess.repositories;

import org.chess.domains.ChessPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<ChessPlayer, Integer> {
}
