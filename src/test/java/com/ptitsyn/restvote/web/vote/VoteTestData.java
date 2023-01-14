package com.ptitsyn.restvote.web.vote;

import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.web.MatcherFactory;
import com.ptitsyn.restvote.web.user.UserTestData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.restaurant1;


public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Vote.class);
    public static Vote vote1 = new Vote(restaurant1, UserTestData.user, LocalDateTime.of(LocalDate.now(),
            LocalTime.of(11, 00)));
}
