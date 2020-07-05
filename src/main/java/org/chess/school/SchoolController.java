package org.chess.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolController {


    private final ChessSchoolService schlService;

    public SchoolController(@Autowired ChessSchoolService schlService) {
        this.schlService = schlService;
    }

    @GetMapping("/schools")
    public ResponseEntity<List<ChessSchoolDTO>> getAllSchools() {
        return new ResponseEntity<>(schlService.getAllSchools(), HttpStatus.OK);
    }

    @GetMapping("/schools/{id}")
    public ResponseEntity<ChessSchoolDTO> getSchool(@PathVariable int id) {
        ChessSchoolDTO school = schlService.getSchool(id);
        return school != null ? new ResponseEntity<>(school, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/schools")
    public ResponseEntity<ChessSchoolDTO> createSchool(@RequestBody ChessSchoolDTO school) {
        if (school.id != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessSchoolDTO answer = schlService.saveUpdateSchool(school);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/schools")
    public ResponseEntity<ChessSchoolDTO> updateSchool(@RequestBody ChessSchoolDTO school) {
        if (school.id == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ChessSchoolDTO answer = schlService.saveUpdateSchool(school);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable int id) {
        schlService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }

}
