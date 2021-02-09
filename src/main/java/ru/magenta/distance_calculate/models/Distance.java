package ru.magenta.distance_calculate.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Distance extends Entity {
    int fromCity;
    int toCity;
    float distance;
}
