package com.bot.maker.service.implementation;

import com.bot.maker.dto.PlaceDTO;
import com.bot.maker.entity.Place;
import com.bot.maker.exception.DuplicateEntityException;
import com.bot.maker.exception.EntityNotFoundException;
import com.bot.maker.repository.PlaceRepository;
import com.bot.maker.service.PlaceService;
import com.bot.maker.validator.implementation.PlaceNameValidator;
import com.bot.maker.validator.implementation.PlaceValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PlaceServiceImplementation implements PlaceService {

    private ModelMapper modelMapper;
    private PlaceRepository placeRepository;
    private PlaceNameValidator placeNameValidator;
    private PlaceValidator placeValidator;

    public Place getPlace(String place) {

        placeNameValidator.validate(place);
        return placeRepository.findByPlace(place.toLowerCase())
                .orElseThrow(() -> {
                    log.error("No such place {}", place);
                    return new EntityNotFoundException("No such place " + place);
                });
    }

    public Place getGuideBotPlace(String place) {

        placeNameValidator.validate(place);
        return placeRepository.findByPlace(place.toLowerCase())
                .orElseGet(() -> {
                    log.error("No such place {}", place);
                    return Place.builder().description("Нет такого города " + place).build();
                });
    }

    public List<PlaceDTO> getPlaces() {

        return modelMapper.map(placeRepository.findAll(),
                new TypeToken<List<PlaceDTO>>() {
                }.getType());
    }

    public void updatePlace(Place place) {

        placeValidator.validate(place);
        Place findFromRepository = getPlace(place.getPlace().toLowerCase());
        findFromRepository.setDescription(place.getDescription());
        placeRepository.save(findFromRepository);
    }

    public void createPlace(Place place) {

        placeValidator.validate(place);
        placeRepository.findByPlace(place.getPlace().toLowerCase()).ifPresent(placePoint -> {
            log.error("Invalid operation - duplicate entity " + place);
            throw new DuplicateEntityException("Invalid operation - duplicate entity " + placePoint);
        });
        placeRepository.save(place);
    }

    public void deletePlace(String place) {

        placeNameValidator.validate(place);
        Place findFromRepository = getPlace(place.toLowerCase());
        placeRepository.delete(findFromRepository);
    }
}
