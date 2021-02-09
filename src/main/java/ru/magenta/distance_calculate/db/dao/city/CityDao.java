package ru.magenta.distance_calculate.db.dao.city;

import org.springframework.dao.DataAccessException;
import ru.magenta.distance_calculate.db.AppDao;
import ru.magenta.distance_calculate.models.City;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CityDao extends AppDao {
    List<City> getAllCities() throws DataAccessException;
    Optional<City> getCityById(int id) throws DataAccessException;
    void batchInsert(Set<City> cities) throws DataAccessException;
}