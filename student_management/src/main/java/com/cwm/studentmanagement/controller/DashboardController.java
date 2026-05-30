package com.cwm.studentmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cwm.studentmanagement.service.EnrollmentService;

@Controller
public class DashboardController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        Map<String, Object> dashboardStats = new HashMap<>();

        dashboardStats.put("totalStudents", 1250);
        dashboardStats.put("totalCourses", 42);
        dashboardStats.put("topPerformingCourse", "CS101");
        dashboardStats.put("studentsEnrolledThisMonth", 37);

        model.addAttribute("dashboardStats", dashboardStats);

        // DATABASE मधले enrolled students fetch होतील
        model.addAttribute("students",
                enrollmentService.getAllEnrollments());

        return "dashboard";
    }
}