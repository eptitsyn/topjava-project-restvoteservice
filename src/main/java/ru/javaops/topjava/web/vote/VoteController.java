package ru.javaops.topjava.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.model.Vote;
import ru.javaops.topjava.repository.VoteRepository;
import ru.javaops.topjava.web.AuthUser;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/vote";

    private final VoteRepository voteRepository;

    @GetMapping("/{date}")
    public List<Vote> getVotesByDate(@AuthenticationPrincipal AuthUser authUser, @PathVariable LocalDate date) {
        return null;
    }

    @PutMapping
    public void setVote(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Vote vote) {
        vote.setUser(authUser.getUser());
        voteRepository.save(vote);
    }
}
