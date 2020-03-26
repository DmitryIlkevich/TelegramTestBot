package com.bot.maker.service;

import com.bot.maker.dto.PlaceDTO;
import com.bot.maker.entity.Place;

import java.util.List;

public interface PlaceService {

    Place getPlace(String place);

    Place getGuideBotPlace(String place);

    List<PlaceDTO> getPlaces();

    void updatePlace(Place place);

    void createPlace(Place place);

    void deletePlace(String place);
}
