package com.bot.maker;

import com.bot.maker.bot.GuideBot;
import com.bot.maker.service.implementation.PlaceServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@SpringBootApplication
@Slf4j
public class GuideTownBotApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PlaceServiceImplementation placeService;

    public static void main(String[] args) {

        ApiContextInitializer.init();
        SpringApplication.run(GuideTownBotApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {

        GuideBot guideBot = new GuideBot(placeService);
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(guideBot);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
