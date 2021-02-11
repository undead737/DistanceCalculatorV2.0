package ru.magenta.distance_calculate.data.importer.XML;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.magenta.distance_calculate.models.City;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcatData {
    City cityFrom;
    City cityTo;
    float distance;
}