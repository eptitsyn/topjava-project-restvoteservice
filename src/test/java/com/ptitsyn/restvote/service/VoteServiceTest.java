package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static com.ptitsyn.restvote.web.RestaurantTestData.restaurant1;
import static com.ptitsyn.restvote.web.RestaurantTestData.restaurant2;
import static com.ptitsyn.restvote.web.user.UserTestData.user;

@SpringBootTest
class VoteServiceTest {

    @Autowired
    private VoteService service;

//    @Test
//    void vote() {
//        Vote vote = new Vote(restaurant1, user, LocalDate.of(2022, Month.FEBRUARY, 1));
//        service.vote(vote, LocalDate.of(2022, Month.FEBRUARY, 1), LocalTime.of(10, 00));
//    }

    @Test
    void voteChangeVote() {
        LocalDate voteDate = LocalDate.of(2022, Month.FEBRUARY, 1);
        service.vote(user, restaurant1, voteDate, LocalTime.of(10, 00));
        service.vote(user, restaurant2, voteDate, LocalTime.of(10, 30));
        Vote vote = service.get(user, voteDate);
        Assert.notNull(vote, "vote is null");
        Assert.isTrue(restaurant2.equals(vote.getRestaurant()), "Not Same Vote");
    }

}