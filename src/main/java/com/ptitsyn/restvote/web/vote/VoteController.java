package com.ptitsyn.restvote.web.vote;

import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.service.VoteService;
import com.ptitsyn.restvote.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public class VoteController {

    static final String REST_URL = "/api/votes";

    private final VoteService voteService;

    @GetMapping
    public Vote getVote(@AuthenticationPrincipal AuthUser authUser) {
        return voteService.get(authUser.getUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setVote(@AuthenticationPrincipal AuthUser authUser, @RequestBody Integer restaurantId) {
        voteService.vote(authUser.getUser(), restaurantId);
    }
}
