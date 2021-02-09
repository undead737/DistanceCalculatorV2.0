package ru.magenta.distance_calculate.math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.magenta.distance_calculate.api.models.request.DistanceCalculatingRequest;
import ru.magenta.distance_calculate.api.models.response.DistanceCalculatingResult;
import ru.magenta.distance_calculate.db.DBService;
import ru.magenta.distance_calculate.exceptions.DataNotFountException;
import ru.magenta.distance_calculate.models.City;

import java.util.ArrayList;
import java.util.List;

@Service
public class MathService {
    private final DBService dbService;

    @Autowired
    public MathService(DBService dbService) {
        this.dbService = dbService;
    }

    public List<DistanceCalculatingResult> calculate(DistanceCalculatingRequest requestModel) throws DataNotFountException {
        List<DistanceCalculatingResult> result = new ArrayList<>();
        for (City from : requestModel.getFromCities()) {
            for (City to : requestModel.getToCities()) {
                switch (requestModel.getType()) {
                    case CROWFLIGHT:
                        result.add(DistanceCalculatingResult.builder()
                                .fromCity(from.getId())
                                .toCity(to.getId())
                                .type(CalculationType.CROWFLIGHT)
                                .resultsSearch(getCrowflightResult(from.getId(), to.getId())).build());
                        break;
                    case DISTANCE_MATRIX:
                        result.add(DistanceCalculatingResult.builder()
                                .fromCity(from.getId())
                                .toCity(to.getId())
                                .type(CalculationType.DISTANCE_MATRIX)
                                .resultsSearch(getDistanceMatrixResult(from.getId(), to.getId())).build());
                        break;
                    case ALL:
                        result.add(DistanceCalculatingResult.builder()
                                .fromCity(from.getId())
                                .toCity(to.getId())
                                .type(CalculationType.CROWFLIGHT)
                                .resultsSearch(getCrowflightResult(from.getId(), to.getId())).build());
                        result.add(DistanceCalculatingResult.builder()
                                .fromCity(from.getId())
                                .toCity(to.getId())
                                .type(CalculationType.DISTANCE_MATRIX)
                                .resultsSearch(getDistanceMatrixResult(from.getId(), to.getId())).build());
                        break;
                    default:
                        throw new DataNotFountException("requests math-type");
                }
            }
        }
        return result;
    }

    private float getDistanceMatrixResult(int idFirst, int idSecond) throws DataNotFountException {
        return dbService.getDistanceFromDistanceTable(idFirst, idSecond).orElseThrow(() ->
                new DataNotFountException(String.format("distance between %d and %d in distance-table", idFirst, idSecond)));
    }

    private float getCrowflightResult(int idFirst, int idSecond) throws DataNotFountException {
        City first = dbService.getCityById(idFirst).orElseThrow(() ->
                new DataNotFountException(String.format("city with id %d", idFirst)));
        City second = dbService.getCityById(idSecond).orElseThrow(() ->
                new DataNotFountException(String.format("city with id %d", idSecond)));
        return (float) DistanceMath.betweenCities(first, second);
    }
}
