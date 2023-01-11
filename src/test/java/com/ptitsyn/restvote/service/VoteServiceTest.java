package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.to.VoteCountTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootTest
class VoteServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void voteChangeVote() {
        LocalDate voteDate = LocalDate.of(2022, Month.FEBRUARY, 1);
//        service.vote(user, restaurant1, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 00)));
//        service.vote(user, restaurant2, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)));
//        Vote vote = service.get(user, voteDate);
//        Assert.notNull(vote, "vote is null");
//        Assert.isTrue(restaurant2.equals(vote.getRestaurant()), "Not Same Vote");
    }

    @Test
    void countVotes() {
        List<VoteCountTo> result = service.getResults(LocalDate.now());
        for (VoteCountTo vote : result) {
            System.out.println(vote.getRestaurant() + " : " + vote.getVoteCount());
        }
    }
}
