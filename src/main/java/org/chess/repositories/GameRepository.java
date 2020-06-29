package org.chess.repositories;

import org.chess.domains.ChessGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<ChessGame, Long> {
}
