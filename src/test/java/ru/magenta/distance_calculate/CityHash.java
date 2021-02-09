package ru.magenta.distance_calculate;

import org.junit.jupiter.api.Test;
import ru.magenta.distance_calculate.models.City;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityHash {
    @Test
    void checkHash(){
        Set<City> expectedResult = new HashSet<>();
        expectedResult.add(City.builder().id(1).name("Самара").latitude(56.00000F).longitude(45.000000F).build());
        expectedResult.add(City.builder().id(2).name("Тольятти").latitude(52.00000F).longitude(40.000000F).build());
        expectedResult.add(City.builder().id(3).name("Рязань").latitude(55.00000F).longitude(48.000000F).build());

        Set<City> actualResult = new HashSet<>();
        actualResult.add(City.builder().id(1).name("Самара").latitude(56.00000F).longitude(45.000000F).build());
        actualResult.add(City.builder().id(1).name("Самара").latitude(56.00000F).longitude(45.000000F).build());
        actualResult.add(City.builder().id(1).name("Тольятти").latitude(56.00000F).longitude(45.000000F).build());
        actualResult.add(City.builder().id(1).name("Рязань").latitude(51.00000F).longitude(40.000000F).build());
        actualResult.add(City.builder().id(2).name("Тольятти").latitude(51.00000F).longitude(40.000000F).build());
        actualResult.add(City.builder().id(3).name("Рязань").latitude(51.00000F).longitude(40.000000F).build());
        actualResult.add(City.builder().id(1).name("Рязань").latitude(51.00000F).longitude(40.000000F).build());
        actualResult.add(City.builder().id(3).name("Самара").latitude(51.00000F).longitude(40.000000F).build());
        actualResult.add(City.builder().id(2).name("Самара").latitude(51.00000F).longitude(40.000000F).build());
        actualResult.add(City.builder().id(2).name("Самара").latitude(51.00000F).longitude(40.000000F).build());
        actualResult.add(City.builder().id(3).name("Рязань").latitude(51.00000F).longitude(40.000000F).build());

        assertEquals(expectedResult, actualResult);
    }
}
