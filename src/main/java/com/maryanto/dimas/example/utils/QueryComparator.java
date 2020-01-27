package com.maryanto.dimas.example.utils;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public interface QueryComparator<T> {

    StringBuilder getQuery(T params);

    MapSqlParameterSource getParameters();

}
