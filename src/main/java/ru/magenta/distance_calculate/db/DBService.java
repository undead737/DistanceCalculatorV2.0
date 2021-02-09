package ru.magenta.distance_calculate.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.magenta.distance_calculate.data.importer.XML.ConcatData;
import ru.magenta.distance_calculate.db.dao.city.CityDao;
import ru.magenta.distance_calculate.db.dao.distance.DistanceDao;
import ru.magenta.distance_calculate.models.City;
import ru.magenta.distance_calculate.models.Distance;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DBService {
    private final CityDao CITY_DAO;
    private final DistanceDao DISTANCE_DAO;

    @Autowired
    public DBService(CityDao cityDao, DistanceDao distance_dao) {
        this.CITY_DAO = cityDao;
        DISTANCE_DAO = distance_dao;
    }

    public List<City> getAllCities() throws DataAccessException{
        return CITY_DAO.getAllCities();
    }

    public Optional<Float> getDistanceFromDistanceTable(int idFirst, int idSecond) throws DataAccessException{
        return DISTANCE_DAO.getDistanceFromDistanceTable(idFirst, idSecond);
    }

    public Optional<City> getCityById(int id) throws DataAccessException{
        return CITY_DAO.getCityById(id);
    }

    @Transactional
    public void batchInsertConcatData(List<ConcatData> concatData) throws DataAccessException{
        CITY_DAO.clearTable();
        DISTANCE_DAO.clearTable();
        CITY_DAO.batchInsert(Stream.concat(
                concatData.stream().map(ConcatData::getCityFrom),
                concatData.stream().map(ConcatData::getCityTo)
            ).collect(Collectors.toSet()));
        DISTANCE_DAO.dropIndex();
        DISTANCE_DAO.batchInsert(concatData.stream().map(x -> new Distance(x.getCityFrom().getId(), x.getCityTo().getId(), x.getDistance())).collect(Collectors.toList()));
        DISTANCE_DAO.createIndex();
    }
}
