package org.chess.school;

import org.chess.school.ChessSchool;
import org.chess.school.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SchoolController {


    private final SchoolRepository sclRep;

    public SchoolController(@Autowired SchoolRepository sclRep) {
        this.sclRep = sclRep;
    }

    @GetMapping("/schools")
    public ResponseEntity<List<ChessSchool>> getAllSchools() {
        return new ResponseEntity<>(sclRep.findAll(), HttpStatus.OK);
    }

    @GetMapping("/schools/{id}")
    public ResponseEntity<ChessSchool> getSchool(@PathVariable int id) {
        Optional<ChessSchool> school = sclRep.findById(id);
        return school
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/schools")
    public ResponseEntity<ChessSchool> createSchool(@RequestBody ChessSchool school) {
        if (school.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessSchool answer = sclRep.save(school);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/schools")
    public ResponseEntity<ChessSchool> updateSchool(@RequestBody ChessSchool school) {
        if (school.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessSchool answer = sclRep.save(school);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable int id) {
        sclRep.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
