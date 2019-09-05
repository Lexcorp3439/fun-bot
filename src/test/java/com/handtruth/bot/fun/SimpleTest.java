package com.handtruth.bot.fun;

import com.handtruth.bot.fun.inst.FunBot;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void sendMsgTest(){
        String msg = "Поздравляю! Теперь вы элита!";
        long chatId = 529797809;
        FunBot.Instance.sendMsg(msg, chatId);
    }
}
