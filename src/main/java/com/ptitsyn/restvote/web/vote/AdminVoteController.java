package com.ptitsyn.restvote.web.vote;

import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("ROLE_ADMIN")
public class AdminVoteController {

    static final String REST_URL = "/api/admin/votes";

    private VoteRepository voteRepository;

    @GetMapping
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @GetMapping("{date}/results")
    public List<Vote> getResultsByDate(@PathVariable @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) LocalDate date) {
        return voteRepository.findByCastedDate(date);
    }
}
