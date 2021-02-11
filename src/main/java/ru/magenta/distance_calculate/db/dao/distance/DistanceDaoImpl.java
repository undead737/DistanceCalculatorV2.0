package ru.magenta.distance_calculate.db.dao.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.magenta.distance_calculate.data.importer.XML.models.DistanceElement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DistanceDaoImpl implements DistanceDao {
    private final JdbcTemplate template;

    @Autowired
    public DistanceDaoImpl(JdbcTemplate template) {
        this.template = template;
    }
    
    @Override
    public void clearTable() throws DataAccessException {
        template.execute("delete from distance");
    }

    @Override
    public Optional<Float> getDistanceFromDistanceTable(int idFirst, int idSecond) throws DataAccessException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("FROM", idFirst);
        params.addValue("TO", idSecond);
        SqlRowSet set = new NamedParameterJdbcTemplate(template)
                .queryForRowSet("select distance from distance where (from_city=:FROM and to_city=:TO) " +
                "or (to_city=:FROM and from_city=:TO)", params);
        return set.next() ? Optional.of(set.getFloat("distance")) : Optional.empty();
    }

    @Override
    public void batchInsert(List<DistanceElement> distances) throws DataAccessException {
        template.batchUpdate("insert into distance (from_city, to_city, distance) values (" +
                        "(select id from city where name=?), (select id from city where name=?), ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, distances.get(i).getNameFrom());
                        ps.setString(2, distances.get(i).getNameTo());
                        ps.setFloat(3, Float.parseFloat(distances.get(i).getDistance()));
                    }

                    @Override
                    public int getBatchSize() {
                        return distances.size();
                    }
                });
    }
}
