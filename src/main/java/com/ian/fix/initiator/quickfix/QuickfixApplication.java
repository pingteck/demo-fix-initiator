package com.ian.fix.initiator.quickfix;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.UnsupportedMessageType;

@Component
@RequiredArgsConstructor
public class QuickfixApplication implements Application {

    private final IncomingMessageCracker incomingMessageCracker;
    private final OutgoingMessageCracker outgoingMessageCracker;

    private SessionID sessionID;

    @Override
    public void onCreate(SessionID sessionID) {

    }

    @Override
    public void onLogon(SessionID sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    public void onLogout(SessionID sessionID) {
        this.sessionID = null;
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

    public void sendMessage(Message message) throws SessionNotFound {
        if (sessionID == null) {
            throw new SessionNotFound("Session not found");
        }
        Session.sendToTarget(message, sessionID);
    }

}
