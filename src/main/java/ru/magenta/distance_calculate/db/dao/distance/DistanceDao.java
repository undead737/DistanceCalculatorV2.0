package ru.magenta.distance_calculate.db.dao.distance;

import org.springframework.dao.DataAccessException;
import ru.magenta.distance_calculate.data.importer.XML.models.DistanceElement;
import ru.magenta.distance_calculate.db.AppDao;

import java.util.List;
import java.util.Optional;

public interface DistanceDao extends AppDao {
    Optional<Float> getDistanceFromDistanceTable(int idFirst, int idSecond) throws DataAccessException;
    void batchInsert(List<DistanceElement> distances) throws DataAccessException;
}