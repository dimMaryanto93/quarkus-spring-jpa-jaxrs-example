package com.maryanto.dimas.example.config;

import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;

@ApplicationScoped
@NoArgsConstructor
public class DatasourceConfiguration {

    private DataSource dataSource;

    @Inject
    public DatasourceConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Produces
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Produces
    public NamedParameterJdbcTemplate namedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
