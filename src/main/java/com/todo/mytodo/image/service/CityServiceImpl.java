package com.todo.mytodo.image.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.mytodo.image.model.City;
import com.todo.mytodo.image.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {

        return (List<City>) repository.findAll();
    }

}
