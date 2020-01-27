package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.ExampleTableDto;
import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.example.mapper.ExampleTableMappers;
import com.maryanto.dimas.example.service.ExampleTableService;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Path("/example")
@NoArgsConstructor
@Produces({MediaType.APPLICATION_JSON})
public class ExampleTableController {

    private ExampleTableService service;

    @Inject
    public ExampleTableController(ExampleTableService service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        Optional<ExampleTable> optional = this.service.findById(id);
        if (!optional.isPresent())
            return Response.noContent().build();

        return Response.ok(optional.get()).build();
    }

    @GET
    @Path("/")
    public Iterable<ExampleTable> list() {
        return service.findAll();
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(@Valid ExampleTableDto.New dto) {
        ExampleTable value = ExampleTableMappers.ExampleNewMapper.converter.convertToEntity(dto);
        value.setCreatedDate(LocalDate.now());
        value.setCreatedTime(LocalDateTime.now());
        value = this.service.save(value);
        return Response.ok(value).build();
    }

    @POST
    @Path("/datatables")
    @Consumes({MediaType.APPLICATION_JSON})
    public DataTablesResponse<ExampleTable> datatables(
            @QueryParam(value = "draw") Long draw,
            @QueryParam(value = "start") Long start,
            @QueryParam(value = "length") Long length,
            @QueryParam(value = "order[0][column]") Long iSortCol0,
            @QueryParam(value = "order[0][dir]") String sSortDir0,
            ExampleTable params) {
        if (params == null) params = new ExampleTable();
        log.info("draw: {}, start: {}, length: {}, orderBy: {}, orderDir: {},  type: {}", draw, start, length, iSortCol0, sSortDir0, params);
        return service.datatables(
                new DataTablesRequest<>(
                        draw != null ? draw : 0l,
                        length != null ? length : 10l,
                        start != null ? start : 0,
                        StringUtils.isNotBlank(sSortDir0) ? sSortDir0 : "asc",
                        iSortCol0 != null ? iSortCol0 : 0l,
                        params)
        );
    }

}
