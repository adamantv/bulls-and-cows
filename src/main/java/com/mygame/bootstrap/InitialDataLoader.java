package com.mygame.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

import com.mygame.domain.*;
import com.mygame.repositories.*;


/**
 * Класс для инициализации базы данных
 */
@Component
public class InitialDataLoader implements ApplicationRunner {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private GameResultsRepository gameResultsRepository;

    @Autowired
    public InitialDataLoader(PasswordEncoder passwordEncoder, UserRepository userRepository, GameResultsRepository gameResultsRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.gameResultsRepository = gameResultsRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("superman");
            user.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(user);

            user = new User();
            user.setUsername("spiderman");
            user.setPassword(passwordEncoder.encode("1234560"));
            userRepository.save(user);

            user = new User();
            user.setUsername("ironman");
            user.setPassword(passwordEncoder.encode("12345678"));
            userRepository.save(user);

            Random R = new Random();
            GameResults gameResults;
            for (int i = 0; i < 15; i++) {
                gameResults = new GameResults();
                gameResults.setDate(new Date(new Date().getTime() - R.nextInt(1000000000)));
                gameResults.setUsername("superman");
                gameResults.setAttempts(R.nextInt(10) + 3);
                gameResultsRepository.save(gameResults);
            }

            for (int i = 0; i < 10; i++) {
                gameResults = new GameResults();
                gameResults.setDate(new Date(new Date().getTime() - R.nextInt(1000000000)));
                gameResults.setUsername("spiderman");
                gameResults.setAttempts(R.nextInt(10) + 4);
                gameResultsRepository.save(gameResults);
            }

            for (int i = 0; i < 5; i++) {
                gameResults = new GameResults();
                gameResults.setDate(new Date(new Date().getTime() - R.nextInt(1000000000)));
                gameResults.setUsername("ironman");
                gameResults.setAttempts(R.nextInt(10) + 5);
                gameResultsRepository.save(gameResults);
            }
        }
    }
}
