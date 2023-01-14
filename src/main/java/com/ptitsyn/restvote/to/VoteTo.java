package com.ptitsyn.restvote.to;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteTo extends BaseTo {

    @NonNull
    private Integer restaurantId;

    public VoteTo(@NonNull Integer id, @NonNull Integer restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }
}
