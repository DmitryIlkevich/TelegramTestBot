package com.bot.maker.service;

import com.bot.maker.dto.PlaceDTO;
import com.bot.maker.entity.Place;
import com.bot.maker.exception.DuplicateEntityException;
import com.bot.maker.exception.EntityNotFoundException;
import com.bot.maker.exception.NotValidDataException;
import com.bot.maker.repository.PlaceRepository;
import com.bot.maker.service.implementation.PlaceServiceImplementation;
import com.bot.maker.validator.implementation.PlaceNameValidator;
import com.bot.maker.validator.implementation.PlaceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceImplementationTest {

    private final String placeName1 = "Kaluga";
    private final String placeName2 = "Minsk";

    @Mock
    private PlaceRepository placeRepository;

    private PlaceServiceImplementation placeServiceImplementation;
    private Place place1;
    private Place place2;

    @BeforeEach
    void setUp() {

        placeServiceImplementation = new PlaceServiceImplementation(new ModelMapper(), placeRepository, new PlaceNameValidator(), new PlaceValidator());

        place1 = Place.builder()
                .id(1L)
                .place(placeName1)
                .description("description")
                .build();
        place2 = Place.builder()
                .id(2L)
                .place(placeName2)
                .description("description")
                .build();
    }

    @Test
    void getPlace_PlaceExist_Success() {
        doReturn(Optional.of(place1)).when(placeRepository).findByPlace(any(String.class));
        assertEquals(place1, placeServiceImplementation.getPlace(placeName1));
    }

    @Test
    void getPlace_PlaceNotExist_ThrowEntityNotFoundException() {
        doReturn(Optional.empty()).when(placeRepository).findByPlace(any(String.class));
        assertThrows(EntityNotFoundException.class, () -> placeServiceImplementation.getPlace(placeName1));
    }

    @Test
    void getPlace_PlaceIsNull_ThrowNotValidDataException() {
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.getPlace(null));
    }

    @Test
    void getPlace_PlaceIsEmpty_ThrowNotValidDataException() {
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.getPlace(""));
    }

    @Test
    void getGuideBotPlace_PlaceExist_Success() {
        doReturn(Optional.of(place1)).when(placeRepository).findByPlace(any(String.class));
        assertEquals(place1, placeServiceImplementation.getGuideBotPlace(placeName1));
    }

    @Test
    void getGuideBotPlace_PlaceNotExist_NotExistMessage() {

        Place placeExpected = Place.builder().description("Нет такого города " + placeName1).build();
        doReturn(Optional.empty()).when(placeRepository).findByPlace(any(String.class));

        assertEquals(placeExpected, placeServiceImplementation.getGuideBotPlace(placeName1));
    }

    @Test
    void getGuideBotPlace_PlaceIsNull_ThrowNotValidDataException() {
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.getGuideBotPlace(null));
    }

    @Test
    void getGuideBotPlace_PlaceIsEmpty_ThrowNotValidDataException() {
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.getGuideBotPlace(""));
    }

    @Test
    void testGetPlaces_TwoPlacesExist_Success() {

        List<Place> places = List.of(place1, place2);
        doReturn(places).when(placeRepository).findAll();
        List<PlaceDTO> placeDTOs = placeServiceImplementation.getPlaces();

        assertTrue(placeDTOs.size() == 2);
    }

    @Test
    void updatePlace_ValidInputPlace_Success() {

        doReturn(place1).when(placeRepository).save(any(Place.class));
        doReturn(Optional.of(place1)).when(placeRepository).findByPlace(any(String.class));
        placeServiceImplementation.updatePlace(place1);

        verify(placeRepository).save(any(Place.class));
        verify(placeRepository).findByPlace(any(String.class));
    }

    @Test
    void updatePlace_ValidPlaceNoSuchPlaceInRepository_ThrowEntityNotFoundException() {
        doReturn(Optional.empty()).when(placeRepository).findByPlace(any(String.class));
        assertThrows(EntityNotFoundException.class, () -> placeServiceImplementation.updatePlace(place1));
    }

    @Test
    void updatePlace_InputPlaceNull_ThrowNotValidDataException() {
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.updatePlace(null));
    }

    @Test
    void updatePlace_InputPlaceNameNull_ThrowNotValidDataException() {
        place1.setPlace(null);
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.updatePlace(place1));
    }

    @Test
    void updatePlace_InputPlaceDescriptionNull_ThrowNotValidDataException() {
        place1.setDescription(null);
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.updatePlace(place1));
    }

    @Test
    void createPlace_NoConflict_Success() {
        doReturn(Optional.empty()).when(placeRepository).findByPlace(anyString());
        placeServiceImplementation.createPlace(place1);

        verify(placeRepository).findByPlace(anyString());
        verify(placeRepository).save(any(Place.class));
    }

    @Test
    void createPlace_SuchPlaceAlreadyExist_ThrowDuplicateEntityException() {
        doReturn(Optional.of(place1)).when(placeRepository).findByPlace(anyString());
        assertThrows(DuplicateEntityException.class, () -> placeServiceImplementation.createPlace(place1));
    }

    @Test
    void createPlace_InputPlaceNull_ThrowNotValidDataException() {
        assertThrows(NotValidDataException.class, () -> placeServiceImplementation.createPlace(null));
    }

    @Test
    void testDeletePlace() {
        doReturn(Optional.of(place1)).when(placeRepository).findByPlace(anyString());
        placeServiceImplementation.deletePlace(placeName1);

        verify(placeRepository).delete(any(Place.class));
    }
}
