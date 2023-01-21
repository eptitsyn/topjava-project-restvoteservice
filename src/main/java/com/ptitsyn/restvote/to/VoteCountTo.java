package com.ptitsyn.restvote.to;

import com.ptitsyn.restvote.model.Restaurant;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteCountTo {

    @NonNull
    private Restaurant restaurant;

    @NonNull
    private Integer voteCount;

    public VoteCountTo(Integer restaurantId, String restaurantName, Integer voteCount) {
        this(new Restaurant(restaurantId, restaurantName), voteCount);
    }
}
