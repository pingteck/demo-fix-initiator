package com.ian.fix.initiator.service;

import com.ian.fix.initiator.model.FixMessageResponse;
import com.ian.fix.initiator.model.MarketDataRequestPostRequest;
import com.ian.fix.initiator.model.NewOrderSinglePostRequest;
import com.ian.fix.initiator.quickfix.QuickfixApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quickfix.SessionNotFound;
import quickfix.fix44.MarketDataRequest;
import quickfix.fix44.NewOrderSingle;

@Slf4j
@Service
@RequiredArgsConstructor
public class FixService {

    private final QuickfixApplication quickfixApplication;

    /*
    Exercise 2

    Task
    Implement sendNewOrderSingle to create and send a NewOrderSingle message
    - Account = 1234
    - Symbol = AAPL
    - OrdType = LIMIT
    - TimeInForce = FILL_OR_KILL

    Outcome
    Successfully receive an ExecutionReport with OrdStatus = PENDING_NEW
     */
    public FixMessageResponse<NewOrderSinglePostRequest> sendNewOrderSingle(
        NewOrderSinglePostRequest request) throws SessionNotFound {
        NewOrderSingle message = null;
        // new NewOrderSingle...
        quickfixApplication.sendMessage(message);
        return new FixMessageResponse<>(request, message);
    }

    /*
    Exercise 3

    Task
    Implement sendMarketDataRequest to create and send a MarketDataRequest message
    - SubscriptionRequestType = SNAPSHOT_UPDATES
    - MarketDepth = TOP_OF_BOOK
    - MDUpdateType = FULL_REFRESH
    - Symbol = AAPl

    Outcome
    Successfully receive a MarketDataSnapshotFullRefresh and SecurityStatus messages
     */
    public FixMessageResponse<MarketDataRequestPostRequest> sendMarketDataRequest(
        MarketDataRequestPostRequest request) throws SessionNotFound {
        MarketDataRequest message = null;
        // new MarketDataRequest...
        quickfixApplication.sendMessage(message);
        return new FixMessageResponse<>(request, message);
    }

}
