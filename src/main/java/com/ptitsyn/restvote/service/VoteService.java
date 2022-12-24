package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.repository.VoteRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {

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

    public void vote(User user, Integer restaurantId) {
        voteRepository.save(new Vote(restaurantRepository.getReferenceById(restaurantId), user, LocalDateTime.now()));
    }
}
