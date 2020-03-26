package com.bot.maker.validator.implementation;

import com.bot.maker.entity.Place;
import com.bot.maker.exception.NotValidDataException;
import com.bot.maker.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class PlaceValidator implements Validator<Place> {

    @Override
    public void validate(Place entity) {

        if (Objects.isNull(entity) || Objects.isNull(entity.getPlace()) || Objects.isNull(entity.getDescription())) {
            log.error("Invalid place entity {}", entity);
            throw new NotValidDataException("Invalid place entity");
        }
    }
}
