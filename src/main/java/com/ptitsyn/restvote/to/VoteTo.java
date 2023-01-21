package com.ptitsyn.restvote.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteTo extends BaseTo {

    private Integer restaurantId;

    public VoteTo(Integer id, Integer restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }
}
