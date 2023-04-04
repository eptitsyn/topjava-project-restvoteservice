package com.ptitsyn.restvote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem extends NamedEntity {

    @PositiveOrZero
    private int price;

    public MenuItem(String name, int price) {
        super(null, name);
        this.price = price;
    }
}
