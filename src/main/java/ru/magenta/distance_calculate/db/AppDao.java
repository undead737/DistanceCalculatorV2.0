package ru.magenta.distance_calculate.db;

import org.springframework.dao.DataAccessException;

/**
 * При импорте новых данных, все таблицы будут чиститься
 */
public interface AppDao {
    void clearTable() throws DataAccessException;
}
