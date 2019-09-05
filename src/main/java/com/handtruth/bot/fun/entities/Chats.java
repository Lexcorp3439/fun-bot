package com.handtruth.bot.fun.entities;

import com.handtruth.bot.fun.tools.BotTools;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chats")
public class Chats {
    @Id
    private Long id;

    @Column(name = "access_first")
    private boolean firstAccess = true;

    @Column(name = "access_second")
    private boolean secondAccess = false;

    @Column(name = "name")
    private String name;

    public Chats() {
    }

    public Chats(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFirstAccess() {
        return firstAccess;
    }

    public void setFirstAccess(boolean firstAccess) {
        this.firstAccess = firstAccess;
    }

    public boolean isSecondAccess() {
        return secondAccess;
    }

    public void setSecondAccess(boolean secondAccess) {
        this.secondAccess = secondAccess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name = *" + name + "*\n" +
                "id = *" + id + "*\n" +
                "firstAccess = *" + firstAccess + "*\n" +
                "secondAccess = *" + secondAccess + "*";

    }
}
