package ru.magenta.distance_calculate.data.importer.XML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.magenta.distance_calculate.data.importer.XML.models.CityElement;
import ru.magenta.distance_calculate.data.importer.XML.models.DistanceElement;
import ru.magenta.distance_calculate.data.importer.XML.models.RootElement;
import ru.magenta.distance_calculate.data.validation.xml_validation.XMLFileValidation;
import ru.magenta.distance_calculate.exceptions.DataImportingException;
import ru.magenta.distance_calculate.data.importer.Importer;
import ru.magenta.distance_calculate.exceptions.ValidateException;

import javax.xml.bind.*;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component("JAXB")
public class JAXBImporter implements Importer {
    private final XMLFileValidation validation;

    @Autowired
    public JAXBImporter(XMLFileValidation validation) {
        this.validation = validation;
    }

    @Override
    public RootElement importData(InputStream file) throws DataImportingException, ValidateException {
        if (file == null) throw new DataImportingException("file can't be null");
        RootElement fileRootElement;
        try {
            fileRootElement = (RootElement) JAXBContext.newInstance(RootElement.class).createUnmarshaller().unmarshal(file);
        } catch (JAXBException e) {
            throw new DataImportingException(e.getMessage());
        }
        if (fileRootElement.getDistances()==null || fileRootElement.getCities()==null)
            throw new DataImportingException("file read error");
        validation.validate(fileRootElement);
        return fileRootElement;
    }
}
