package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.ExampleTableDto;
import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.example.mapper.ExampleTableMappers;
import com.maryanto.dimas.example.service.ExampleTableService;

import javax.inject.Inject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

@Path("/example")
public class ExampleTableController {

    @Inject
    private ExampleTableService service;

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Set<ExampleTable> list() {
        return new HashSet<>(service.findAll());
    }

    @POST
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(@Valid ExampleTableDto.New dto) {
        ExampleTable value = ExampleTableMappers.ExampleNewMapper.converter.convertToEntity(dto);
        try {
            value = this.service.save(value);
            return Response.ok(value).build();
        } catch (SystemException | NotSupportedException e) {
            return Response.serverError().build();
        }
    }
}
