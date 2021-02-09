package ru.magenta.distance_calculate.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.magenta.distance_calculate.exceptions.DataImportingException;
import ru.magenta.distance_calculate.data.importer.Importer;
import ru.magenta.distance_calculate.db.DBService;

import java.io.InputStream;

@Service
public class DataService {
    private final Importer IMPORTER;
    private final DBService DB_SERVICE;

    @Autowired
    public DataService(Importer importer, DBService service){
        this.IMPORTER = importer;
        this.DB_SERVICE = service;
    }

    public void importData(InputStream file) throws DataImportingException, DataAccessException {
        DB_SERVICE.batchInsertConcatData(IMPORTER.importData(file));
    }
}
