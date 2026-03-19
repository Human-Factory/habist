package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.EnrollmentService;

public class EnrollmentController {

    private EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
}
