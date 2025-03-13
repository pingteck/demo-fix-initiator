package com.ian.fix.initiator.controller;

import com.ian.fix.initiator.model.FixMessageResponse;
import com.ian.fix.initiator.model.MarketDataRequestPostRequest;
import com.ian.fix.initiator.model.NewOrderSinglePostRequest;
import com.ian.fix.initiator.service.FixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quickfix.SessionNotFound;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final FixService fixService;

    @PostMapping("/trade/v1/new")
    public ResponseEntity<FixMessageResponse<NewOrderSinglePostRequest>> newOrderSingle(
        @RequestBody NewOrderSinglePostRequest request) throws SessionNotFound {
        return ResponseEntity.ok(fixService.sendNewOrderSingle(request));
    }

    @PostMapping("/market/v1/request")
    public ResponseEntity<FixMessageResponse<MarketDataRequestPostRequest>> marketDataRequest(
        @RequestBody MarketDataRequestPostRequest request) throws SessionNotFound {
        return ResponseEntity.ok(fixService.sendMarketDataRequest(request));
    }

}
