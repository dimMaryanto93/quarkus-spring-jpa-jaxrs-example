package com.maryanto.dimas.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ExampleTableDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New {

        @NotNull
        @NotEmpty
        private String name;
        @NotNull
        private Integer counter;
        @NotNull
        @Min(0)
        private BigDecimal currency;
        private String description;
        @NotNull
        @Min(0)
        @Max(100)
        private Double floating;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {

        @NotNull
        @NotEmpty
        private String id;
        @NotNull
        @NotEmpty
        private String name;
        @NotNull
        private Integer counter;
        @NotNull
        @Min(0)
        private BigDecimal currency;
        private String description;
        @NotNull
        @Min(0)
        @Max(100)
        private Double floating;
    }
}
