package com.ian.fix.initiator.quickfix;

import org.springframework.stereotype.Component;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.SessionID;
import quickfix.fix44.Logon;

@Component
public class OutgoingMessageCracker extends MessageCracker {

    @Override
    public void onMessage(Message message, SessionID sessionID) {

    }

    /*
    Exercise 1

    Task
    Implement onMessage to attach the correct credentials to Logon Message
    - Username = username
    - Password = password

    Outcome
    Successfully establish FIX connectivity with acceptor
     */
    public void onMessage(Logon message, SessionID sessionID) {
        // message.set...
    }

}
