package ru.magenta.distance_calculate.math;

import org.junit.jupiter.api.Test;
import ru.magenta.distance_calculate.models.City;

import static org.junit.jupiter.api.Assertions.*;

class DistanceMathTest {

    //Допустимая погрешность в 10 км
    private static final double deltaDistanceBetweenCities = 10.0;

    @Test
    void betweenCitiesA() {
        double expectedResult = 1.64;
        double actualResult = DistanceMath.betweenCities(
                new City("", 53.264095F, 50.295125F),
                new City("", 53.263476F, 50.319342F));
        assertEquals(expectedResult, actualResult, 10.0);
    }

    @Test
    void betweenCitiesB() {
        double expectedResult = 3773.950695;
        double actualResult = DistanceMath.betweenCities(
                new City("", 53.480792F, 48.660830F),
                new City("", 64.647681F, 114.728630F));
        assertEquals(expectedResult, actualResult, 10.0);
    }
}