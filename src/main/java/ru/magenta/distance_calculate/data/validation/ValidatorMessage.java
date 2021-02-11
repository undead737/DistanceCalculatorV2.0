package ru.magenta.distance_calculate.data.validation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidatorMessage {
    TypeValidatorError typeMessage;
    String message;
}
