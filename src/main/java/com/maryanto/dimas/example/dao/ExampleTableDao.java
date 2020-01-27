package com.maryanto.dimas.example.dao;

import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.example.repository.ExampleTableRepository;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoDataTablesPattern;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ExampleTableDao implements DaoDataTablesPattern<ExampleTable> {

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

    @Override
    public List<ExampleTable> datatables(DataTablesRequest<ExampleTable> params) {
        return null;
    }

    @Override
    public Long datatables(ExampleTable param) {
        return null;
    }
}
