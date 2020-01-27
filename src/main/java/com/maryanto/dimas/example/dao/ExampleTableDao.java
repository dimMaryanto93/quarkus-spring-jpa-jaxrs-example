package com.maryanto.dimas.example.dao;

import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.example.repository.ExampleTableRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ExampleTableDao {

    @Inject
    private ExampleTableRepository repository;

    public List<ExampleTable> list() {
        return this.repository.findAll().list();
    }

    public Optional<ExampleTable> findById(String id) {
        return this.repository.findByIdOptional(id);
    }

    public ExampleTable save(ExampleTable value) {
        this.repository.persistAndFlush(value);
        return value;
    }

}
