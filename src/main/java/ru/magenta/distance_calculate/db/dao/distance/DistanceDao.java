package ru.magenta.distance_calculate.db.dao.distance;

import org.springframework.dao.DataAccessException;
import ru.magenta.distance_calculate.db.AppDao;
import ru.magenta.distance_calculate.models.Distance;

import java.util.List;
import java.util.Optional;

public interface DistanceDao extends AppDao {
    void createIndex() throws DataAccessException;
    void dropIndex() throws DataAccessException;
    Optional<Float> getDistanceFromDistanceTable(int idFirst, int idSecond) throws DataAccessException;
    void batchInsert(List<Distance> distances) throws DataAccessException;
}