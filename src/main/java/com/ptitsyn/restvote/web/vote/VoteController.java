package com.ptitsyn.restvote.web.vote;

import com.ptitsyn.restvote.service.VoteService;
import com.ptitsyn.restvote.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public class VoteController {

    static final String REST_URL = "/api/vote";

    private final VoteService voteService;

    @PutMapping("/{restaurant_id}")
    public void setVote(@AuthenticationPrincipal AuthUser authUser, @PathVariable int restaurant_id) {
        voteService.Vote(authUser, restaurant_id);
    }
}
