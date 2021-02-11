package ru.magenta.distance_calculate.data.importer;

import ru.magenta.distance_calculate.data.importer.XML.models.RootElement;
import ru.magenta.distance_calculate.exceptions.DataImportingException;
import ru.magenta.distance_calculate.exceptions.ValidateException;

import java.io.InputStream;

/**
 * Импорт данных
 */
public interface Importer{
    RootElement importData(InputStream file) throws DataImportingException, ValidateException;
}
