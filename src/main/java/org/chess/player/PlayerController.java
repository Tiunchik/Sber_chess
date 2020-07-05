package org.chess.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final ChessPlayerService plrService;

    public PlayerController(@Autowired ChessPlayerService plrService) {
        this.plrService = plrService;
    }

    @GetMapping("/players/best")
    public ResponseEntity<List<ChessPlayerDTO>> getAllBsetPlayersInLast5Minutes() {
        return new ResponseEntity<>(plrService.getAllBestPlayersInLast5Minutes(), HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<List<ChessPlayerDTO>> getAllPlayers() {
        return new ResponseEntity<>(plrService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<ChessPlayerDTO> getPlayer(@PathVariable int id) {
        ChessPlayerDTO plr = plrService.getPlayer(id);
        return plr != null ? new ResponseEntity<>(plr, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/players")
    public ResponseEntity<ChessPlayerDTO> createPlayer(@RequestBody ChessPlayerDTO plr) {
        if (plr.id != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessPlayerDTO answer = plrService.saveUpdatePlayer(plr);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/players")
    public ResponseEntity<ChessPlayerDTO> updatePlayer(@RequestBody ChessPlayerDTO plr) {
        if (plr.id == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessPlayerDTO answer = plrService.saveUpdatePlayer(plr);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        if (plrService.deletePlayer(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

