package com.ian.fix.initiator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MarketDataRequestPostRequest {

    private String mdReqId;
    private String symbol;
    private Character subscriptionRequestType;
    private Integer marketDepth;
    private Integer mdUpdateType;

}
