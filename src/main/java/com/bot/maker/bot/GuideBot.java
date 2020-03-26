package com.bot.maker.bot;

import com.bot.maker.service.implementation.PlaceServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@AllArgsConstructor
@Component
@Slf4j
public class GuideBot extends TelegramLongPollingBot {

    @Autowired
    private PlaceServiceImplementation placeService;

    @Override
    public void onUpdateReceived(Update update) {

        String response = generateResponse(update.getMessage().getText());
        sendResponse(response, update.getMessage().getChatId());
    }

    @Override
    public String getBotUsername() {
        return "testTownGuideBot";
    }

    @Override
    public String getBotToken() {
        return "1078945734:AAEr3AwuT2fPIzmhY8FhALKJdawsDcwVoJg";
    }

    private String generateResponse(String request) {

        StringBuilder response = new StringBuilder();

        switch (request) {

            case "города":
                placeService.getPlaces()
                        .stream()
                        .forEach(placeDTO -> response.append(placeDTO.getPlace()).append("\n"));
                break;
            default:
                response.append(placeService.getGuideBotPlace(request).getDescription());
        }
        return response.toString();
    }

    private void sendResponse(String responce, Long chatId) {

        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(responce);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
