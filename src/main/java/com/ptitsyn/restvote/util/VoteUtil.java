package com.ptitsyn.restvote.util;

import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.to.VoteTo;

public class VoteUtil {
    public static VoteTo asTo(Vote vote) {
        return new VoteTo(vote.id(), vote.getRestaurant().id());
    }
}
