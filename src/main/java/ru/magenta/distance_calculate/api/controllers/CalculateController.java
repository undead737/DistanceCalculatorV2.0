package ru.magenta.distance_calculate.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import ru.magenta.distance_calculate.api.models.request.DistanceCalculatingRequest;
import ru.magenta.distance_calculate.math.MathService;

@RestController
@RequestMapping("/api/distance")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculateController {
    private final MathService mathService;

    @Autowired
    public CalculateController(MathService mathService) {
        this.mathService = mathService;
    }

    @PostMapping("calculate")
    public ResponseEntity<?> calculate(@RequestBody DistanceCalculatingRequest requestModel){
        try {
            return new ResponseEntity<>(mathService.calculate(requestModel), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
