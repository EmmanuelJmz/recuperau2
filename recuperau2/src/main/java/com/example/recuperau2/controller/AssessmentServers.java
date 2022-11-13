package com.example.recuperau2.controller;

import com.example.recuperau2.model.assessment.BeanAssessment;
import com.example.recuperau2.model.teacher.DaoTeacher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/assessments")
public class AssessmentServers {
    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanAssessment> getAll() {
        return new DaoTeacher().getAssessment();
    }
}
