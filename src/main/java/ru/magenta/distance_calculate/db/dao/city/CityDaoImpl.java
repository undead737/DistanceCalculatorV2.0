package ru.magenta.distance_calculate.db.dao.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.magenta.distance_calculate.models.City;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CityDaoImpl implements CityDao {
    private final JdbcTemplate template;

    @Autowired
    public CityDaoImpl(JdbcTemplate template) {
        this.template = template;
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
    public void batchInsert(Set<City> cities) throws DataAccessException {
        template.batchUpdate("insert into city (id, name, latitude, longitude) values (?, ?, ?, ?)",
                cities.stream().map(x->new Object[]{x.getId(), x.getName(), x.getLatitude(),x.getLatitude()}).collect(Collectors.toList()));
    }

    @Override
    public void clearTable() throws DataAccessException {
        template.execute("delete from city");
    }
}