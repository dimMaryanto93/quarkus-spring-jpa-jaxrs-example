package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.dao.ExampleTableDao;
import com.maryanto.dimas.example.entity.ExampleTable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
public class ExampleTableService {

    @Inject
    private ExampleTableDao dao;

    public Optional<ExampleTable> findById(String id) {
        return this.dao.findById(id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ExampleTable save(ExampleTable value) {
        value = dao.save(value);
        return value;
    }

    public List<ExampleTable> findAll() {
        return dao.list();
    }

}
