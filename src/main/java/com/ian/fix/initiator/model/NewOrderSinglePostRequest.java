package com.ian.fix.initiator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewOrderSinglePostRequest {

    private String clOrdId;
    private String account;
    private String symbol;

    private Double orderQty;
    private Double price;

    private Character side;
    private Character ordType;
    private Character timeInForce;

}
