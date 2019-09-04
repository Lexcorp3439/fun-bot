package com.handtruth.bot.fun.utils;

public enum Command {
    start("/start"),
    info("/info"),
    help("/help"),
    update("/update"),
    set_time("set_time"),
    disband("/disband");

    private String name;

    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
