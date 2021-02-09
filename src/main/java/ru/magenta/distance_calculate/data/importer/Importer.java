package ru.magenta.distance_calculate.data.importer;

import ru.magenta.distance_calculate.data.importer.XML.ConcatData;
import ru.magenta.distance_calculate.exceptions.DataImportingException;

import java.io.InputStream;
import java.util.List;

/**
 * Импорт данных
 */
public interface Importer{
    List<ConcatData> importData(InputStream file) throws DataImportingException;
}
