package com.example.recuperau2.model;

import com.example.recuperau2.utils.Response;

import java.util.List;

public interface Repository <T>{

    List<T> findAll();

    T findById(Long id);

    Response<T> save(T object);

    Response<T> update(T object);

    //Response<T> remove(Long id);
    Response<T> remove(Long id);

}