package com.solbeg.testtask.citiesshower.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class City {

    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String photo;
}
