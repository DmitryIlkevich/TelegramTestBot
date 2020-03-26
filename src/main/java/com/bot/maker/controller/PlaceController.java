package com.bot.maker.controller;

import com.bot.maker.dto.PlaceDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlaceController {

    PlaceDTO getPlace(@PathVariable String place);

    List<PlaceDTO> getPlaces();

    void updatePlace(@RequestBody PlaceDTO placeDTO);

    void createPlace(@RequestBody PlaceDTO placeDTO);

    void deletePlace(@PathVariable String place);
}
