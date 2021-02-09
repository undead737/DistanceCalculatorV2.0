package ru.magenta.distance_calculate.data.importer.XML;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Корневой элемент XML. Содерит список объединенных данных (город от + город до + расстояние)
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
@XmlType(name = "data")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RootElement {
    List<ConcatData> data;
}