package com.mygame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.*;

import com.mygame.domain.GameResults;
import com.mygame.gameLogic.Game;
import com.mygame.gameLogic.Pair;
import com.mygame.repositories.GameResultsRepository;

/**
 * Контроллер для адресов "/" (страница игры) и "/makeAttempt" (результат хода пользователя)
 */
@Controller
public class IndexController {
    private Map<String, Game> games;
    private GameResultsRepository gameResultsRepository;

    public IndexController(GameResultsRepository gameResultsRepository) {
        games = new HashMap<>();
        this.gameResultsRepository = gameResultsRepository;
    }


    @GetMapping("/")
    public String get(Principal principal) {
        games.put(principal.getName(), new Game());
        return "index";
    }

    /**
     * Принимается число от пользователя и возвращается результат хода - число "быков" и число "коров".
     * Если пользователь угадал число, результат игры сохраняется в репозиторий
     */
    @GetMapping("/makeAttempt")
    @ResponseBody
    public Pair<Integer, Integer> makeAttempt(@RequestParam(value = "numbers[]") int[] numbers, Principal principal) {
        Game game = games.get(principal.getName());
        Pair<Integer, Integer> result = game.makeAttempt(numbers);
        if (result.getFirst() == 4) {
            GameResults gameResults = new GameResults();
            gameResults.setDate(new Date());
            gameResults.setUsername(principal.getName());
            gameResults.setAttempts(game.getAttempts());
            gameResultsRepository.save(gameResults);
        }
        return result;
    }
}
