package com.mygame.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

/**
 * Сущность "результаты игр"
 */
@Entity
@Table(name = "GameResults")
public class GameResults {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;


    @Column(name = "Date", nullable = false)
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date date;


    @Column(name = "Username", nullable = false)
    private String username;


    @Column(name = "Attempts", nullable = false)
    private int attempts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
