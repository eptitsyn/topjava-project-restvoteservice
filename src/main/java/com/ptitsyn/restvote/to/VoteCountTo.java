package com.ptitsyn.restvote.to;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.model.Vote;
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

    public VoteCountTo(@NonNull Restaurant restaurant, @NonNull Long count) {
        this(restaurant, count.intValue());
    }

    public VoteCountTo(@NonNull Vote vote, @NonNull Integer count) {
        this(vote.getRestaurant(), count);
    }

    public VoteCountTo(@NonNull Vote vote, @NonNull Long count) {
        this(vote.getRestaurant(), count.intValue());
    }
}
