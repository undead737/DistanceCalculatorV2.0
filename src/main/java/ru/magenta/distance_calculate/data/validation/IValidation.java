package ru.magenta.distance_calculate.data.validation;

import ru.magenta.distance_calculate.exceptions.ValidateException;

public interface IValidation<T> {
    void validate(T args) throws ValidateException;
}
