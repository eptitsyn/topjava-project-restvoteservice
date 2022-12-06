package com.ptitsyn.restvote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Item {

    private double description;
    private double price;
}
