package com.handtruth.bot.fun.utils;

public class Action {
    public Act act;
    public String messages;

    public Action(Act act, String messages) {
        this.act = act;
        this.messages = messages;
    }

    public enum Act {
        Message, Photo, No
    }
}
