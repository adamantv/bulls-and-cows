package com.mygame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

import com.mygame.gameLogic.Pair;
import com.mygame.gameLogic.UserStats;
import com.mygame.repositories.GameResultsRepository;

/**
 * Контроллер для адресов "/rating" (рейтинг пользователей), "/rating/topUsers" (рейтинг игроков)
 * и "/rating/myTopPosition" (позиция пользователя в рейтинге)
 */
@Controller
public class RatingController {
    private final GameResultsRepository gameResultsRepository;

    public RatingController(GameResultsRepository gameResultsRepository) {
        this.gameResultsRepository = gameResultsRepository;
    }

    /**
     * Возвращает массив статистик пользователей, отсортированный по возрастанию среднего
     * количества ходов, в JSON формате
     */
    @GetMapping("/rating/topUsers")
    @ResponseBody
    public Iterable<UserStats> getTopUsers() {
        return gameResultsRepository.getAllRatingUnits();
    }

    /** Возвращает страницу рейтинга пользователей */
    @RequestMapping("/rating")
    public String get() {
        return "rating";
    }

    /**
     * Возвращает позицию пользователя в рейтинге и его статистику.
     * Получает статистику всех пользователей, отсортированную по возрастанию среднего количества ходов.
     * Находит и возвращает позицию пользователя в списке и его статистику.
     * Если статистика пользователя не найдена (у него нет завершенных игр), возвращает значения
     * по умолчанию
     */
    @GetMapping("/rating/myTopPosition")
    @ResponseBody
    public Pair<Integer, UserStats> getMyRating(Principal principal) {
        List<UserStats> ratingList = gameResultsRepository.getAllRatingUnits();
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).getUsername().equals(principal.getName()))
                return new Pair<>(i + 1, ratingList.get(i));
        }
        return new Pair<>(-1, new UserStats(principal.getName(), 0, -1, -1));
    }
}
