package com.example.recuperau2.controller;

import com.example.recuperau2.model.assessment.BeanAssessment;
import com.example.recuperau2.model.teacher.DaoTeacher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api/average")
public class AverageServers {
    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanAssessment> getAll() {
        return new DaoTeacher().average();

    }
}
