package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.dao.ExampleTableDao;
import com.maryanto.dimas.example.entity.ExampleTable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.*;
import java.util.List;

@ApplicationScoped
public class ExampleTableService {

    @Inject
    private UserTransaction transaction;
    @Inject
    private ExampleTableDao dao;

    public ExampleTable save(ExampleTable value) throws SystemException, NotSupportedException {
        try {
            transaction.begin();
            value = dao.save(value);
            transaction.commit();
            return value;
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    public List<ExampleTable> findAll(){
        return dao.list();
    }
}
