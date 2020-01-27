package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.ExampleTableDto;
import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.example.mapper.ExampleTableMappers;
import com.maryanto.dimas.example.service.ExampleTableService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Path("/example")
@Produces({MediaType.APPLICATION_JSON})
public class ExampleTableController {

    @Inject
    private ExampleTableService service;

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
    public Set<ExampleTable> list() {
        return new HashSet<>(service.findAll());
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
}
