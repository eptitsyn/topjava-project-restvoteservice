package com.ptitsyn.restvote.web.vote;

import com.ptitsyn.restvote.service.VoteService;
import com.ptitsyn.restvote.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public class VoteController {

    static final String REST_URL = "/api/votes";

    private final VoteService voteService;

//    @GetMapping
//    public Vote getVote(@AuthenticationPrincipal AuthUser authUser) {
//        return voteService.get(authUser.getUser());
//    }

    @PutMapping
    public void setVote(@AuthenticationPrincipal AuthUser authUser, @RequestBody int restaurant_id) {
        // voteService.vote(authUser.getUser(), restaurant_id);
    }
}
