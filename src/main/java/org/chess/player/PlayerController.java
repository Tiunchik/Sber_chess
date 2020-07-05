package org.chess.player;

import org.chess.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlayerController {


    private final PlayerRepository plrRep;

    private final GameRepository gmrRep;

    public PlayerController(@Autowired PlayerRepository plrRep, @Autowired GameRepository gmrRep) {
        this.plrRep = plrRep;
        this.gmrRep = gmrRep;
    }

    @GetMapping("/players/best")
    public ResponseEntity<List<ChessPlayer>> getAllBsetPlayersInLast5Minutes() {
        return new ResponseEntity<>(plrRep.bestInLastFiveMinutes(), HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<List<ChessPlayer>> getAllPlayers() {
        return new ResponseEntity<>(plrRep.findAll(), HttpStatus.OK);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<ChessPlayer> getPlayer(@PathVariable int id) {
        Optional<ChessPlayer> plr = plrRep.findById(id);
        return plr
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/players")
    public ResponseEntity<ChessPlayer> createPlayer(@RequestBody ChessPlayer plr) {
        if (plr.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessPlayer answer = plrRep.save(plr);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/players")
    public ResponseEntity<ChessPlayer> updatePlayer(@RequestBody ChessPlayer plr) {
        if (plr.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessPlayer answer = plrRep.save(plr);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        plrRep.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

