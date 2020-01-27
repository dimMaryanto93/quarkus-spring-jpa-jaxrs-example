package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.ExampleTable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExampleTableRepository extends PagingAndSortingRepository<ExampleTable, String> {
}
