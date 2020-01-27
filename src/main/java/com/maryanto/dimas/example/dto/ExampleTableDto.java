package com.maryanto.dimas.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExampleTableDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New {

        @NotNull
        @NotEmpty
        private String name;
        private LocalDate createdDate;
        private LocalDateTime createdTime;
        private Boolean active;
        private Integer counter;
        private BigDecimal currency;
        private String description;
        private Double floating;
    }
}
