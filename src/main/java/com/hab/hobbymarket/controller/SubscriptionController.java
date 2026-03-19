package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.SubscriptionService;

public class SubscriptionController {

    private SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
}
