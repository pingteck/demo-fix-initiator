package com.ian.fix.initiator.service;

import com.ian.fix.initiator.model.FixMessageResponse;
import com.ian.fix.initiator.model.MarketDataRequestPostRequest;
import com.ian.fix.initiator.model.NewOrderSinglePostRequest;
import com.ian.fix.initiator.quickfix.QuickfixApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quickfix.SessionNotFound;
import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.MDReqID;
import quickfix.field.MDUpdateType;
import quickfix.field.MarketDepth;
import quickfix.field.NoMDEntryTypes;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Symbol;
import quickfix.field.TimeInForce;
import quickfix.field.TransactTime;
import quickfix.fix44.MarketDataRequest;
import quickfix.fix44.MarketDataRequest.NoRelatedSym;
import quickfix.fix44.NewOrderSingle;

@Slf4j
@Service
@RequiredArgsConstructor
public class FixService {

    private final QuickfixApplication quickfixApplication;

    public FixMessageResponse<NewOrderSinglePostRequest> sendNewOrderSingle(
        NewOrderSinglePostRequest request) throws SessionNotFound {
        NewOrderSingle message = new NewOrderSingle(
            new ClOrdID(request.getClOrdId()),
            new Side(request.getSide()),
            new TransactTime(),
            new OrdType(request.getOrdType())
        );
        message.set(new Account(request.getAccount()));
        message.set(new Symbol(request.getSymbol()));
        message.set(new OrderQty(request.getOrderQty()));
        message.set(new Price(request.getPrice()));
        message.set(new TimeInForce(request.getTimeInForce()));
        quickfixApplication.sendMessage(message);
        return new FixMessageResponse<>(request, message);
    }

    public FixMessageResponse<MarketDataRequestPostRequest> sendMarketDataRequest(
        MarketDataRequestPostRequest request) throws SessionNotFound {
        MarketDataRequest message = new MarketDataRequest(
            new MDReqID(request.getMdReqId()),
            new SubscriptionRequestType(request.getSubscriptionRequestType()),
            new MarketDepth(request.getMarketDepth())
        );
        message.set(new MDUpdateType(request.getMdUpdateType()));
        message.set(new NoMDEntryTypes(0));
        NoRelatedSym noRelatedSym = new NoRelatedSym();
        noRelatedSym.set(new Symbol(request.getSymbol()));
        message.addGroup(noRelatedSym);
        quickfixApplication.sendMessage(message);
        return new FixMessageResponse<>(request, message);
    }

}
