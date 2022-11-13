package com.example.recuperau2.controller;

import com.example.recuperau2.model.student.BeanStudent;
import com.example.recuperau2.model.student.DaoStudent;
import com.example.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.beans.Beans;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/students")
public class StudentServers {
    Map<String, Object> response = new HashMap<>();
    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanStudent> getAll(){
        return new DaoStudent().findAll();
    }


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save(BeanStudent estudiante){
        System.out.println(estudiante.getName());
        Response<BeanStudent> result = new DaoStudent().save(estudiante);
        response.put("result", result);
        return response;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update(BeanStudent estudiante){
        System.out.println(estudiante.getName());
        Response<BeanStudent> result = new DaoStudent().update(estudiante);
        response.put("result", result);
        return response;
    }
}
