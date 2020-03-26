package com.bot.maker.validator.implementation;

import com.bot.maker.exception.NotValidDataException;
import com.bot.maker.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class PlaceNameValidator implements Validator<String> {

    @Override
    public void validate(String placeName) {

        if (Objects.isNull(placeName) || placeName.isBlank()) {
            log.error("Invalid place name {}", placeName);
            throw new NotValidDataException("Invalid place name " + placeName);
        }
    }
}
