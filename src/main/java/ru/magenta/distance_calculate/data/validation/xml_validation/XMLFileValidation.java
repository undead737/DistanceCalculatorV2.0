package ru.magenta.distance_calculate.data.validation.xml_validation;

import org.javatuples.Pair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.magenta.distance_calculate.data.importer.XML.models.CityElement;
import ru.magenta.distance_calculate.data.importer.XML.models.DistanceElement;
import ru.magenta.distance_calculate.data.importer.XML.models.RootElement;
import ru.magenta.distance_calculate.data.validation.IValidation;
import ru.magenta.distance_calculate.data.validation.TypeValidatorError;
import ru.magenta.distance_calculate.data.validation.ValidatorMessage;
import ru.magenta.distance_calculate.exceptions.ValidateException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public final class XMLFileValidation implements IValidation<RootElement> {
    private final Set<ValidatorMessage> resultImport;

    public XMLFileValidation() {
        resultImport = new HashSet<>();
    }

    @Override
    public void validate(RootElement root) throws ValidateException {
        checkCities(root);
        checkDistances(root);
        if (resultImport.stream().map(x->x.getTypeMessage()==TypeValidatorError.ERROR).collect(Collectors.toSet()).size()>0){
            StringBuilder resultMessage = new StringBuilder();
            for (ValidatorMessage message : resultImport){
                resultMessage.append(message.getTypeMessage()).append(": ").append(message.getMessage()).append("; ");
            }
            throw new ValidateException(resultMessage.toString());
        }
    }

    /**
     * Смотрим дубли названий городов
     * + формат широты и долготы
     */
    private void checkCities(RootElement root) {
        boolean hasDuplicateError = false;
        boolean hasFormatError = false;
        Set<String> result = new HashSet<>();
        for (CityElement element : root.getCities()) {
            if (!hasDuplicateError && result.contains(element.getName())){
                hasDuplicateError = true;
                resultImport.add(ValidatorMessage.builder()
                        .typeMessage(TypeValidatorError.ERROR)
                        .message("city duplicate!")
                        .build());
            } else result.add(element.getName());
            Matcher matcher = Pattern.compile("^([\\d]+[.][\\d]+)[ ]([\\d]+[.][\\d]+)$").matcher(
                    new StringBuilder().append(element.getLatitude()).append(" ").append(element.getLongitude()));
            if (!hasFormatError && !matcher.find()){
                hasFormatError = true;
                resultImport.add(ValidatorMessage.builder()
                        .typeMessage(TypeValidatorError.ERROR)
                        .message("wrong format of latitude or longitude!")
                        .build());
            }
        }
    }

    /**
     * Смотрим формат дистанции между городами
     * + все ли города из блока дистанций есть в блоке определения самих городов
     * + дубли путей между городами
     */
    private void checkDistances(RootElement root) {
        Set<String> named = root.getCities().stream().map(CityElement::getName).collect(Collectors.toSet());
        boolean hasDuplicateError = false;
        boolean hasDistanceFormatError = false;
        boolean notFoundCityError = false;
        Set<Pair<String, String>> result = new HashSet<>();
        for (DistanceElement element : root.getDistances()) {
            if (!hasDistanceFormatError) {
                Matcher matcher = Pattern.compile("^([0-9]*\\.?[0-9]+)$").matcher(element.getDistance());
                if (!matcher.find()){
                    hasDistanceFormatError = true;
                    resultImport.add(ValidatorMessage.builder()
                            .typeMessage(TypeValidatorError.ERROR)
                            .message("wrong distances format!")
                            .build());
                }
            }
            if (!notFoundCityError && (!named.contains(element.getNameFrom()) || !named.contains(element.getNameTo()))){
                notFoundCityError = true;
                resultImport.add(ValidatorMessage.builder()
                        .typeMessage(TypeValidatorError.ERROR)
                        .message("not found in cities list!")
                        .build());
            }
            Pair<String, String> distinctCitiesPair = new Pair<>(element.getNameFrom(), element.getNameTo());
            if (!hasDuplicateError && result.contains(distinctCitiesPair)){
                hasDuplicateError = true;
                resultImport.add(ValidatorMessage.builder()
                        .typeMessage(TypeValidatorError.ERROR)
                        .message("duplicate distances between cities!")
                        .build());
            } else {
                Pair<String, String> distinctCitiesPairInverted = new Pair<>(element.getNameTo(), element.getNameFrom());
                result.add(distinctCitiesPair);
                result.add(distinctCitiesPairInverted);
            }
        }
    }
}
