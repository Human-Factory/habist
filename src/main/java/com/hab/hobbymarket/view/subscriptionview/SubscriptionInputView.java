package com.hab.hobbymarket.view.subscriptionview;

import com.hab.hobbymarket.controller.SubscriptionController;
import java.util.Scanner;

public class SubscriptionInputView {

    private SubscriptionController subscriptionController;
    private Scanner sc = new Scanner(System.in);

    public SubscriptionInputView(SubscriptionController subscriptionController) {
        this.subscriptionController = subscriptionController;
    }
}
