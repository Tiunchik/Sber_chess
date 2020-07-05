package org.chess.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private final GameRepository gamRep;

    public GameController(@Autowired GameRepository gamRep) {
        this.gamRep = gamRep;
    }

    @GetMapping("/games")
    public ResponseEntity<List<ChessGame>> getAllGames() {
        return new ResponseEntity<>(gamRep.findAll(), HttpStatus.OK);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<ChessGame> getGame(@PathVariable long id) {
        Optional<ChessGame> game = gamRep.findById(id);
        return game
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/games")
    public ResponseEntity<ChessGame> createGame(@RequestBody ChessGame game) {
        if (game.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessGame answer = gamRep.save(game);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/games")
    public ResponseEntity<ChessGame> updateGame(@RequestBody ChessGame game) {
        if (game.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessGame answer = gamRep.save(game);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable long id) {
        gamRep.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
