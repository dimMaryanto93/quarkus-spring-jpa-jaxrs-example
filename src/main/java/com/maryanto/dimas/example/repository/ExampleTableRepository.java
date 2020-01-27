package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.ExampleTable;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleTableRepository implements PanacheRepositoryBase<ExampleTable, String> {

}
