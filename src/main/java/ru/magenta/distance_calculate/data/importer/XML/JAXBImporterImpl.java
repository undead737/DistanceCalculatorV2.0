package ru.magenta.distance_calculate.data.importer.XML;

import org.springframework.stereotype.Component;
import ru.magenta.distance_calculate.exceptions.DataImportingException;
import ru.magenta.distance_calculate.data.importer.Importer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;

@Component("JAXB")
public class JAXBImporterImpl implements Importer {
    @Override
    public List<ConcatData> importData(InputStream file) throws DataImportingException {
        if (file == null) throw new DataImportingException("file can't be null");
        try {
            RootElement fileRootElement = (RootElement) JAXBContext.newInstance(RootElement.class).createUnmarshaller().unmarshal(file);
            return fileRootElement.getData();
        } catch (JAXBException e) {
            throw new DataImportingException(e.getMessage());
        }
    }
}
