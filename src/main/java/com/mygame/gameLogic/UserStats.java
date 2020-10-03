package com.mygame.gameLogic;

import java.io.Serializable;

/**
 * Класс для передачи статистик пользователей
 */
public class UserStats implements Serializable {

    private String username;

    private long gamesPlayed;

    private int bestAttempts;

    private double averageAttempts;

    public UserStats(String username, long gamesPlayed, int bestAttempts, double averageAttempts) {
        this.username = username;
        this.gamesPlayed = gamesPlayed;
        this.bestAttempts = bestAttempts;
        this.averageAttempts = averageAttempts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(long gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getBestAttempts() {
        return bestAttempts;
    }

    public void setBestAttempts(int bestAttempts) {
        this.bestAttempts = bestAttempts;
    }

    public double getAverageAttempts() {
        return averageAttempts;
    }

    public void setAverageAttempts(double averageAttempts) {
        this.averageAttempts = averageAttempts;
    }
}
