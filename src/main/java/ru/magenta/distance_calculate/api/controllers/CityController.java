package ru.magenta.distance_calculate.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.magenta.distance_calculate.db.DBService;

@RestController
@RequestMapping("/api/city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {
    private final DBService dbService;

    @Autowired
    public CityController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("getAll")
    public ResponseEntity<?> importData(){
        try {
            return new ResponseEntity<>(dbService.getAllCities(), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
