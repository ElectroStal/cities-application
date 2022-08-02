package com.solbeg.testtask.citiesshower.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BusinessEntity {
    private int currentPage;
    private int pageSize;
    private int amountOfEntities;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private List<City> entities = new ArrayList<>();
}
