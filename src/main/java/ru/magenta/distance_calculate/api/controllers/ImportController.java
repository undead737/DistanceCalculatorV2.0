package ru.magenta.distance_calculate.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.magenta.distance_calculate.data.DataService;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "http://localhost:4200")
public class ImportController {
    private final DataService dataService;

    @Autowired
    public ImportController(DataService dataService){
        this.dataService= dataService;
    }

    @PostMapping("import")
    public ResponseEntity<?> importData(@RequestParam("file") MultipartFile file){
        try {
            dataService.importData(file.getInputStream());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}