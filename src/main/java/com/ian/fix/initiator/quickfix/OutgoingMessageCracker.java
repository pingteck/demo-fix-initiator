package com.ian.fix.initiator.quickfix;

import org.springframework.stereotype.Component;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.SessionID;
import quickfix.field.Password;
import quickfix.field.Username;
import quickfix.fix44.Logon;

@Component
public class OutgoingMessageCracker extends MessageCracker {

    @Override
    public void onMessage(Message message, SessionID sessionID) {

    }

    public void onMessage(Logon message, SessionID sessionID) {
        message.set(new Username("username"));
        message.set(new Password("password"));
    }

}
