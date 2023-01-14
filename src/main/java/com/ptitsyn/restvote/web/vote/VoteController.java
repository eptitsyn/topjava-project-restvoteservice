package com.ptitsyn.restvote.web.vote;

import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.service.VoteService;
import com.ptitsyn.restvote.to.VoteTo;
import com.ptitsyn.restvote.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;


@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public class VoteController {
    static final String REST_URL = "/api/votes";
    private final VoteService voteService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public ResponseEntity<Vote> castUserVote(@AuthenticationPrincipal AuthUser authUser,
                                             @Valid @RequestBody VoteTo voteTo) {
        int restaurantId = voteTo.getRestaurantId();
        Vote vote = voteService.create(restaurantId, authUser.getUser());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(vote.getId()).toUri();
        System.out.println(vote.getRestaurant());
        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @GetMapping
    Vote getTodayLastUserVote(@AuthenticationPrincipal AuthUser authUser) {
        return voteService.findLastVoteForUserForDate(authUser.getUser(), LocalDate.now());
    }
}
