package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.repository.UserRepository;
import com.ptitsyn.restvote.repository.VoteRepository;
import com.ptitsyn.restvote.to.VoteCountTo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final UserRepository userRepository;
    @NonNull VoteRepository voteRepository;
    @NonNull RestaurantRepository restaurantRepository;
    @Value("${app.voteFinishTime}")
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime voteFinishTime;
    private Clock clock = Clock.systemDefaultZone();

    public Vote get(User user) {
        return get(user, LocalDate.now());
    }

    public Vote get(User user, LocalDate date) {
        return voteRepository.findFirstByUser_IdAndCastedBetweenOrderByCastedDesc(user.id(), date.atStartOfDay(),
                date.plusDays(1).atStartOfDay());
    }

    public Vote create(int restaurantId, User user) {
        Vote vote = new Vote(restaurantRepository.getReferenceById(restaurantId),
                userRepository.getReferenceById(user.id()), LocalDateTime.now());
        return voteRepository.save(vote);
    }

    public Vote findLastVoteForUserForDate(User user, LocalDate date) {
        return voteRepository.findLastVoteForUserForDate(user, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    public List<VoteCountTo> getResults(LocalDate date) {
        return voteRepository.findAllResultByLastVote(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }
}
