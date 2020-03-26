package com.bot.maker.controller.implementation;

import com.bot.maker.controller.PlaceController;
import com.bot.maker.dto.PlaceDTO;
import com.bot.maker.entity.Place;
import com.bot.maker.service.implementation.PlaceServiceImplementation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class PlaceControllerImplementation implements PlaceController {

    private ModelMapper modelMapper;
    private PlaceServiceImplementation placeService;

    @GetMapping("/places/{place}")
    @ResponseStatus(HttpStatus.OK)
    public PlaceDTO getPlace(@PathVariable String place) {
        return modelMapper.map(placeService.getPlace(place), PlaceDTO.class);
    }

    @GetMapping("/places")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaceDTO> getPlaces() {
        return placeService.getPlaces();
    }

    @PutMapping("/places/{place}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePlace(@RequestBody PlaceDTO placeDTO) {
        placeService.updatePlace(modelMapper.map(placeDTO, Place.class));
    }

    @PostMapping("/places")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlace(@RequestBody PlaceDTO placeDTO) {
        placeService.createPlace(modelMapper.map(placeDTO, Place.class));
    }

    @DeleteMapping("/places/{place}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlace(@PathVariable String place) {
        placeService.deletePlace(place);
    }
}
