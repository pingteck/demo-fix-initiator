package com.ian.fix.initiator;

import org.springframework.stereotype.Component;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.FileStoreFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RuntimeError;
import quickfix.ScreenLogFactory;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

@Component
public class Initiator {

    public Initiator(QuickfixApplication application, SessionSettings sessionSettings)
        throws ConfigError, RuntimeError {

        final MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);
        final LogFactory logFactory = new ScreenLogFactory();
        final MessageFactory messageFactory = new DefaultMessageFactory();

        final SocketInitiator socketInitiator = SocketInitiator.newBuilder()
            .withApplication(application)
            .withMessageStoreFactory(messageStoreFactory)
            .withSettings(sessionSettings)
            .withLogFactory(logFactory)
            .withMessageFactory(messageFactory)
            .build();

        socketInitiator.start();
    }

}
