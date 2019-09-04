package com.handtruth.bot.fun.entities;

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

    public Chats() {
    }

    public Chats(Long id) {
        this.id = id;
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
}
