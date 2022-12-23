package com.ptitsyn.restvote.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

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
//        service.vote(user, restaurant1, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 00)));
//        service.vote(user, restaurant2, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)));
//        Vote vote = service.get(user, voteDate);
//        Assert.notNull(vote, "vote is null");
//        Assert.isTrue(restaurant2.equals(vote.getRestaurant()), "Not Same Vote");
    }

}
