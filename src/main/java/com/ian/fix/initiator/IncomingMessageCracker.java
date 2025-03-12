package com.ian.fix.initiator;

import org.springframework.stereotype.Component;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.SessionID;

@Component
public class IncomingMessageCracker extends MessageCracker {

    @Override
    public void onMessage(Message message, SessionID sessionID) {

    }

}
