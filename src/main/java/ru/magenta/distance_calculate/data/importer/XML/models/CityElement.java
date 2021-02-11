package ru.magenta.distance_calculate.data.importer.XML.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@XmlType(name = "cityElement")
public class CityElement {
    String name;
    String latitude;
    String longitude;
}
