package com.ian.fix.initiator.model;

import lombok.Getter;
import lombok.ToString;
import quickfix.Message;

@Getter
@ToString
public class FixMessageResponse<T> {

    private final T request;
    private final String message;

    public FixMessageResponse(T request, Message message) {
        this.request = request;
        this.message = message.toString();
    }

}
