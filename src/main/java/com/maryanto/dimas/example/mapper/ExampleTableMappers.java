package com.maryanto.dimas.example.mapper;

import com.maryanto.dimas.example.dto.ExampleTableDto;
import com.maryanto.dimas.example.entity.ExampleTable;
import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public class ExampleTableMappers {

    @Mapper
    public interface ExampleNewMapper extends ObjectMapper<ExampleTable, ExampleTableDto.New> {
        ExampleNewMapper converter = Mappers.getMapper(ExampleNewMapper.class);
    }

    @Mapper
    public interface ExampleUpdateMapper extends ObjectMapper<ExampleTable, ExampleTableDto.Update> {
        ExampleUpdateMapper converter = Mappers.getMapper(ExampleUpdateMapper.class);
    }

}
