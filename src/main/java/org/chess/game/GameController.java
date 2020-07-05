package org.chess.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private final ChessGameService gameService;

    public GameController(@Autowired ChessGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ResponseEntity<List<ChessGameDTO>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<ChessGameDTO> getGame(@PathVariable long id) {
        ChessGameDTO game = gameService.getGame(id);
        return game != null ? new ResponseEntity<>(game, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/games")
    public ResponseEntity<ChessGameDTO> createGame(@RequestBody ChessGameDTO game) {
        if (game.id != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessGameDTO answer = gameService.saveUpdateGame(game);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/games")
    public ResponseEntity<ChessGameDTO> updateGame(@RequestBody ChessGameDTO game) {
        if (game.id == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessGameDTO answer = gameService.saveUpdateGame(game);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable long id) {
        if (gameService.deleteGame(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
