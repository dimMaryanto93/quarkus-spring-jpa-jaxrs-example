package com.maryanto.dimas.example.dao;

import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.example.repository.ExampleTableRepository;
import com.maryanto.dimas.example.utils.QueryComparator;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoDataTablesPattern;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
@NoArgsConstructor
public class ExampleTableDao implements DaoDataTablesPattern<ExampleTable> {

    private ExampleTableRepository repository;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Inject
    public ExampleTableDao(
            ExampleTableRepository repository,
            NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.repository = repository;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public Iterable<ExampleTable> list() {
        return this.repository.findAll();
    }

    public Optional<ExampleTable> findById(String id) {
        return this.repository.findById(id);
    }

    public ExampleTable save(ExampleTable value) {
        this.repository.save(value);
        return value;
    }

    @Override
    public List<ExampleTable> datatables(DataTablesRequest<ExampleTable> params) {
        //language=PostgreSQL
        String baseQuery = "select id,\n" +
                "       name,\n" +
                "       created_date as createdDate,\n" +
                "       created_time as createdTime,\n" +
                "       is_active as active,\n" +
                "       counter,\n" +
                "       currency,\n" +
                "       description,\n" +
                "       floating\n" +
                "from example_table\n" +
                "where 1 = 1";
        MapSqlParameterSource mapSqlParameters = new MapSqlParameterSource();
        DataTablesMapping dt = new DataTablesMapping(baseQuery, mapSqlParameters);
        mapSqlParameters = dt.getParameters();
        StringBuilder query = dt.getQuery(params.getValue());

        query.append(" limit :limit offset :offset ");
        mapSqlParameters.addValue("limit", params.getLength());
        mapSqlParameters.addValue("offset", params.getStart());

        return namedJdbcTemplate.query(query.toString(), mapSqlParameters, new BeanPropertyRowMapper<>(ExampleTable.class));
    }

    @Override
    public Long datatables(ExampleTable value) {
        //language=PostgreSQL
        String baseQuery = "select count(*) as rows\n" +
                "from example_table\n" +
                "where 1 = 1";
        MapSqlParameterSource mapSqlParameters = new MapSqlParameterSource();
        DataTablesMapping dt = new DataTablesMapping(baseQuery, mapSqlParameters);
        mapSqlParameters = dt.getParameters();
        StringBuilder query = dt.getQuery(value);

        return namedJdbcTemplate.queryForObject(query.toString(), mapSqlParameters, (resultSet, i) -> resultSet.getLong(1));
    }

    private class DataTablesMapping implements QueryComparator<ExampleTable> {

        private final MapSqlParameterSource parameter;
        private final StringBuilder builder;

        public DataTablesMapping(String query, MapSqlParameterSource parameterSource) {
            this.parameter = parameterSource;
            this.builder = new StringBuilder(query);
        }

        @Override
        public StringBuilder getQuery(ExampleTable value) {
            if (StringUtils.isNotBlank(value.getId())) {
                this.builder.append(" and id = :id");
                this.parameter.addValue("id", value.getId());
            }

            if (StringUtils.isNotBlank(value.getName())) {
                this.builder.append(" and lower(name) like lower(:name)");
                this.parameter.addValue("name",
                        new StringBuilder("%")
                                .append(value.getName())
                                .append("%").toString());
            }

            if (value.getCreatedDate() != null) {
                this.builder.append(" and created_date = :createdDate");
                this.parameter.addValue("createdDate", value.getCreatedDate());
            }

            if (value.getCreatedTime() != null) {
                this.builder.append(" and created_time = :createdTime ");
                this.parameter.addValue("createdTime", value.getCreatedTime());
            }

            if (value.getActive() != null) {
                this.builder.append(" and is_active = :isActive ");
                this.parameter.addValue("isActive", value.getActive());
            }

            if (value.getCounter() != null) {
                this.builder.append(" and counter = :counter ");
                this.parameter.addValue("counter", value.getCounter());
            }

            if (value.getCurrency() != null) {
                this.builder.append(" and currency = :currency ");
                this.parameter.addValue("currency", value.getCurrency());
            }

            if (StringUtils.isNotBlank(value.getDescription())) {
                this.builder.append(" and lower(description) like lower(:description) ");
                this.parameter.addValue("description",
                        new StringBuilder("%")
                                .append(value.getDescription())
                                .append("%").toString());
            }

            if (value.getFloating() != null) {
                this.builder.append(" and floating = :floating ");
                this.parameter.addValue("floating", value.getFloating());
            }

            return this.builder;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameter;
        }
    }

}
