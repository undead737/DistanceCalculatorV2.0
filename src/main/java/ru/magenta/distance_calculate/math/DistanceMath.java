package ru.magenta.distance_calculate.math;

import ru.magenta.distance_calculate.models.City;

public final class DistanceMath {
    //Радиус земли
    public static final double EARTH_RADIUS = 6372.795;

    /**
     * В километрах. Точность 6 знаков
     */
    public static double betweenCities(City firstCity, City secondCity) {
        return Math.ceil((2 * Math.asin(Math.sqrt(Math.pow(Math.sin(((secondCity.getLatitude() * Math.PI / 180) - (firstCity.getLatitude() * Math.PI / 180)) / 2), 2)
                + Math.cos(firstCity.getLatitude() * Math.PI / 180) * Math.cos(secondCity.getLatitude() * Math.PI / 180)
                * Math.pow(Math.sin(((secondCity.getLongitude() * Math.PI / 180) - (firstCity.getLongitude() * Math.PI / 180)) / 2), 2))) * EARTH_RADIUS) * Math.pow(10,6)) / Math.pow(10,6);
    }
}
