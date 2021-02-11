package ru.magenta.distance_calculate.data.importer.XML.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlType(name = "rootElement")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RootElement {
    @XmlElementWrapper(name = "cities")
    @XmlElement(name = "cityElement")
    List<CityElement> cities;
    @XmlElementWrapper(name = "distances")
    @XmlElement(name = "distanceElement")
    List<DistanceElement> distances;
}