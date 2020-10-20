package com.todo.mytodo.image.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.mytodo.image.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}