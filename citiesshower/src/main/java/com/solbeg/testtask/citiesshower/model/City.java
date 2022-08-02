package com.solbeg.testtask.citiesshower.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class City {
    @Id
    private int id;
    private String name;
    private String photo;
}
