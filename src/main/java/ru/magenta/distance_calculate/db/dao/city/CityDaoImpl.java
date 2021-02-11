package ru.magenta.distance_calculate.db.dao.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.magenta.distance_calculate.data.importer.XML.models.CityElement;
import ru.magenta.distance_calculate.models.City;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CityDaoImpl implements CityDao {
    private final JdbcTemplate template;

    @Autowired
    public CityDaoImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createIndex() throws DataAccessException {
        template.execute("CREATE INDEX search_index ON city(name)");
    }

    @Override
    public void dropIndex() throws DataAccessException {
        template.execute("drop index search_index on city");
    }

    @Override
    public List<City> getAllCities() throws DataAccessException {
        return template.query("select * from city",
                (rs, i) ->
                        City.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .latitude(rs.getFloat("latitude"))
                                .longitude(rs.getFloat("longitude"))
                                .build());
    }

    public Optional<City> getCityById(int id) throws DataAccessException{
        SqlRowSet set = template.queryForRowSet("select * from city where id=?", id);
        return set.next() ? Optional.ofNullable(City.builder()
                .id(set.getInt("id"))
                .name(set.getString("name"))
                .latitude(set.getFloat("latitude"))
                .longitude(set.getFloat("longitude"))
                .build()) : Optional.empty();
    }

    @Override
    public void batchInsert(List<CityElement> cities) throws DataAccessException {
        template.batchUpdate("insert into city (name, latitude, longitude) values (?, ?, ?)",
                cities.stream().map(x->new Object[]{x.getName(), x.getLatitude(),x.getLongitude()}).collect(Collectors.toList()));
    }

    @Override
    public void clearTable() throws DataAccessException {
        template.execute("delete from city");
    }
}