package com.solbeg.testtask.citiesshower.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class City extends BaseEntity {

    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String photo;
}
