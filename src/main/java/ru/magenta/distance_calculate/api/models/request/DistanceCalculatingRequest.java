package ru.magenta.distance_calculate.api.models.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.magenta.distance_calculate.math.CalculationType;
import ru.magenta.distance_calculate.models.City;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuppressWarnings("unused")
public class DistanceCalculatingRequest {
    CalculationType type;
    List<City> fromCities;
    List<City> toCities;
}