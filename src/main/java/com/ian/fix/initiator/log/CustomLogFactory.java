package com.ian.fix.initiator.log;

import quickfix.LocationAwareLogFactory;
import quickfix.Log;
import quickfix.SessionID;

public class CustomLogFactory implements LocationAwareLogFactory {

    private final boolean enableHeartbeatLog;

    public CustomLogFactory(boolean enableHeartbeatLog) {
        this.enableHeartbeatLog = enableHeartbeatLog;
    }

    @Override
    public Log create(SessionID sessionID, String callerFQCN) {
        return new CustomLog(sessionID, enableHeartbeatLog);
    }

    @Override
    public Log create(SessionID sessionID) {
        return create(sessionID, null);
    }
}
