package ru.magenta.distance_calculate.api.models.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.magenta.distance_calculate.math.CalculationType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistanceCalculatingResult {
    CalculationType type;
    int fromCity;
    int toCity;
    float resultsSearch;
}
