package com.ian.fix.initiator.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.InvalidMessage;
import quickfix.Log;
import quickfix.Message;
import quickfix.MessageUtils;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.fix44.Heartbeat;

public class CustomLog implements Log {

    private final SessionID sessionID;
    private final boolean enableHeartbeatLog;

    private final Logger logger;

    public CustomLog(SessionID sessionID, boolean enableHeartbeatLog) {
        this.sessionID = sessionID;
        this.enableHeartbeatLog = enableHeartbeatLog;
        this.logger = LoggerFactory.getLogger(sessionID.toString());
    }

    @Override
    public void clear() {

    }

    @Override
    public void onIncoming(String s) {
        handleMessage(s, "Incoming");
    }

    @Override
    public void onOutgoing(String s) {
        handleMessage(s, "Outgoing");
    }

    @Override
    public void onEvent(String s) {
        logger.info("[Event] {}", s);
    }

    @Override
    public void onErrorEvent(String s) {
        logger.error("[Error] {}", s);
    }

    private void handleMessage(String s, String category) {
        StringBuilder log = new StringBuilder("[").append(category).append("] ");
        try {
            Message message = MessageUtils.parse(Session.lookupSession(sessionID), s);
            if (enableHeartbeatLog || !(message instanceof Heartbeat)) {
                log.append(message.getClass().getSimpleName()).append(": ").append(s);
                logger.info(log.toString());
            }
        } catch (InvalidMessage e) {
            log.append("Invalid message: ").append(s);
            logger.error(log.toString());
        }
    }

}
