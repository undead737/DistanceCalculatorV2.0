package ru.magenta.distance_calculate.data.importer.XML;

import org.junit.jupiter.api.Test;
import ru.magenta.distance_calculate.data.validation.xml_validation.XMLFileValidation;
import ru.magenta.distance_calculate.exceptions.DataImportingException;

import static org.junit.jupiter.api.Assertions.*;

class JAXBImporterImplTest {

    @Test
    void importData(){
        JAXBImporter jaxbImporter = new JAXBImporter(new XMLFileValidation());
        Throwable thrown = assertThrows(DataImportingException.class, () -> jaxbImporter.importData(null));
        assertNotNull(thrown.getMessage());
    }
}