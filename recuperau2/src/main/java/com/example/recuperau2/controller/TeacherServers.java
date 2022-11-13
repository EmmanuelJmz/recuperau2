package com.example.recuperau2.controller;

import com.example.recuperau2.model.teacher.BeanTeacher;
import com.example.recuperau2.model.teacher.DaoTeacher;
import com.example.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/teacher")
public class TeacherServers {
    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanTeacher> getAll(){
        return new DaoTeacher().findAll();
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update(BeanTeacher docente){
        System.out.println(docente.getName());
        Response<BeanTeacher> result = new DaoTeacher().update(docente);
        response.put("result", result);
        return response;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save(BeanTeacher docente){
        System.out.println(docente.getName());
        Response<BeanTeacher> result = new DaoTeacher().save(docente);
        response.put("result", result);
        return response;
    }
}
