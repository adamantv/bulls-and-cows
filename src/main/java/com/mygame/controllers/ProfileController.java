package com.mygame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import com.mygame.domain.GameResults;
import com.mygame.gameLogic.UserStats;
import com.mygame.repositories.GameResultsRepository;

/**
 * Контроллер для адресов "/profile" (профиль пользователя), "/profile/gameResults" (история игр)
 * и "/profile/stats" (статистика пользователя)
 */
@Controller
public class ProfileController {
    private final GameResultsRepository gameResultsRepository;

    public ProfileController(GameResultsRepository gameResultsRepository) {
        this.gameResultsRepository = gameResultsRepository;
    }

    /** Возвращает страницу профиля пользователя */
    @GetMapping("/profile")
    public String get() {
        return "profile";
    }

    /**
     * Возвращает статистику пользователя (количество завершенных игр, минимальное количество ходов,
     * среднее количество ходов) в JSON-формате. Если статистика не найдена (пользователь не завершил
     * ни одной игры), возвращает пустую статистику
     */
    @GetMapping("/profile/stats")
    @ResponseBody
    public UserStats getStats(Principal principal) {
        UserStats stats = gameResultsRepository.getRatingByUsername(principal.getName());
        if (stats == null) return new UserStats(principal.getName(), 0, -1, -1);
        else return stats;
    }

    /**
     * Возвращает массив результатов игр пользователя в JSON-формате
     */
    @GetMapping("/profile/gameResults")
    @ResponseBody
    public Iterable<GameResults> getGameResults(Principal principal) {
        return gameResultsRepository.getAllByUsernameOrderByDate(principal.getName());
    }
}
