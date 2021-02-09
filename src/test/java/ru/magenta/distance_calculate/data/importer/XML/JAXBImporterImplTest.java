package ru.magenta.distance_calculate.data.importer.XML;

import org.junit.jupiter.api.Test;
import ru.magenta.distance_calculate.exceptions.DataImportingException;

import static org.junit.jupiter.api.Assertions.*;

class JAXBImporterImplTest {

    @Test
    void importData(){
        JAXBImporterImpl jaxbImporter = new JAXBImporterImpl();
        Throwable thrown = assertThrows(DataImportingException.class, () -> {
            jaxbImporter.importData(null);
        });
        assertNotNull(thrown.getMessage());
    }
}