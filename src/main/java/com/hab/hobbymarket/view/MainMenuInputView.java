package com.hab.hobbymarket.view;

import com.hab.hobbymarket.view.enrollmentview.EnrollmentInputView;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionInputView;
import com.hab.hobbymarket.view.wishlistview.WishlistInputView;
import java.util.Scanner;

public class MainMenuInputView {

    private MemberInputView memberInputView;
    private EnrollmentInputView enrollmentInputView;
    private SubscriptionInputView subscriptionInputView;
    private WishlistInputView wishlistInputView;
    private Scanner sc = new Scanner(System.in);

    public MainMenuInputView(MemberInputView memberInputView,
                             EnrollmentInputView enrollmentInputView,
                             SubscriptionInputView subscriptionInputView,
                             WishlistInputView wishlistInputView) {
        this.memberInputView = memberInputView;
        this.enrollmentInputView = enrollmentInputView;
        this.subscriptionInputView = subscriptionInputView;
        this.wishlistInputView = wishlistInputView;
    }

    public void displayMainMenu() {
    }
}
