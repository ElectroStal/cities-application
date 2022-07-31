package com.solbeg.testtask.citiesshower.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessEntity {
    private int currentPage;
    private int pageSize;
    private int amountOfEntities;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private List<City> entities = new ArrayList<>();
}
