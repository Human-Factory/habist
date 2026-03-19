package com.hab.hobbymarket.view.enrollmentview;

import com.hab.hobbymarket.controller.EnrollmentController;
import java.util.Scanner;

public class EnrollmentInputView {

    private EnrollmentController enrollmentController;
    private Scanner sc = new Scanner(System.in);

    public EnrollmentInputView(EnrollmentController enrollmentController) {
        this.enrollmentController = enrollmentController;
    }
}
