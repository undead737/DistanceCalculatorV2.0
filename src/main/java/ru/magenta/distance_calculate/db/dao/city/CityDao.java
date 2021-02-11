package ru.magenta.distance_calculate.db.dao.city;

import org.springframework.dao.DataAccessException;
import ru.magenta.distance_calculate.data.importer.XML.models.CityElement;
import ru.magenta.distance_calculate.db.AppDao;
import ru.magenta.distance_calculate.models.City;

import java.util.List;
import java.util.Optional;

public interface CityDao extends AppDao {
    void createIndex() throws DataAccessException;
    void dropIndex() throws DataAccessException;
    List<City> getAllCities() throws DataAccessException;
    Optional<City> getCityById(int id) throws DataAccessException;
    void batchInsert(List<CityElement> cities) throws DataAccessException;
}