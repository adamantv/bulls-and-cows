package com.mygame.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mygame.domain.GameResults;
import com.mygame.gameLogic.UserStats;

import java.util.List;

/**
 * Репозиторий статистики результатов игр
 */
@Repository
public interface GameResultsRepository extends CrudRepository<GameResults, Long> {
    /**
     * Возвращает статистику пользователя
     */
    @Query("SELECT new com.mygame.gameLogic.UserStats(gameResults.username, COUNT(gameResults), MIN(gameResults.attempts), AVG(gameResults.attempts)) " +
            "FROM GameResults gameResults " +
            "WHERE gameResults.username = ?1 " +
            "GROUP BY gameResults.username")
    UserStats getRatingByUsername(String username);

    /**
     * Возвращает список истории игр пользователя, отсортированный по возрастанию даты и времени
     */
    List<GameResults> getAllByUsernameOrderByDate(String username);

    /**
     * Возвращает список статистик всех пользователей, отсортированный по возрастанию среднего количества ходов
     */
    @Query("SELECT new com.mygame.gameLogic.UserStats(gameResults.username, COUNT(gameResults), MIN(gameResults.attempts), AVG(gameResults.attempts)) " +
            "FROM GameResults gameResults " +
            "GROUP BY gameResults.username " +
            "ORDER BY AVG(gameResults.attempts) ASC")
    List<UserStats> getAllRatingUnits();

}
