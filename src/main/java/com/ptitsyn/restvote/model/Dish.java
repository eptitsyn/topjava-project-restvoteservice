package com.ptitsyn.restvote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Embeddable
public class Dish {

    @NotEmpty
    private String description;

    @PositiveOrZero
    private double price;
}
