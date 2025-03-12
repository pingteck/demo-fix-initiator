package com.ian.fix.initiator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;

@Component
@RequiredArgsConstructor
public class QuickfixApplication implements Application {

    public final IncomingMessageCracker incomingMessageCracker;
    public final OutgoingMessageCracker outgoingMessageCracker;

    @Override
    public void onCreate(SessionID sessionID) {

    }

    @Override
    public void onLogon(SessionID sessionID) {

    }

    @Override
    public void onLogout(SessionID sessionID) {

    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        try {
            outgoingMessageCracker.crack(message, sessionID);
        } catch (UnsupportedMessageType | IncorrectTagValue | FieldNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID)
        throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        try {
            incomingMessageCracker.crack(message, sessionID);
        } catch (UnsupportedMessageType e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        try {
            outgoingMessageCracker.crack(message, sessionID);
        } catch (UnsupportedMessageType | FieldNotFound | IncorrectTagValue e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fromApp(Message message, SessionID sessionID)
        throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        incomingMessageCracker.crack(message, sessionID);
    }

}
