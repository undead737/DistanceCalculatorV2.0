package ru.magenta.distance_calculate.data.importer.XML.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(name = "distanceElement")
public class DistanceElement {
    String nameFrom;
    String nameTo;
    String distance;
}
