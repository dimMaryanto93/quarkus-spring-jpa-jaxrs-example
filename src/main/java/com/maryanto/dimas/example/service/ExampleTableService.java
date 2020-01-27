package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.dao.ExampleTableDao;
import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceDataTablesPattern;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
@Transactional(readOnly = true)
@NoArgsConstructor
public class ExampleTableService implements ServiceDataTablesPattern<ExampleTable> {

    private ExampleTableDao dao;

    @Inject
    public ExampleTableService(ExampleTableDao dao) {
        this.dao = dao;
    }

    public Optional<ExampleTable> findById(String id) {
        return this.dao.findById(id);
    }

    @Transactional
    public ExampleTable save(ExampleTable value) {
        value = dao.save(value);
        return value;
    }

    public Iterable<ExampleTable> findAll() {
        return dao.list();
    }

    @Override
    public DataTablesResponse<ExampleTable> datatables(DataTablesRequest<ExampleTable> value) {
        List<ExampleTable> datas = dao.datatables(value);
        Long rows = dao.datatables(value.getValue());
        return new DataTablesResponse<>(datas, value.getDraw(), rows, rows);
    }
}
