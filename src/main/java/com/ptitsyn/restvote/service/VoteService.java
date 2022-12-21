package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.model.Restaurant;
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
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {

    @NonNull
    private final Clock clock = Clock.systemDefaultZone();

    @NonNull VoteRepository voteRepository;
    @NonNull RestaurantRepository restaurantRepository;
    @Value("${app.voteFinishTime}")
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime voteFinishTime;

    public void vote(@NonNull User user, int restaurantId) {
        vote(user, restaurantRepository.getReferenceById(restaurantId), LocalDate.now(clock), LocalTime.now(clock));
    }

    public void vote(User user, Restaurant restaurant, LocalDate localDate, LocalTime localTime) {
        if (localTime.isAfter(voteFinishTime)) {
            throw new IllegalStateException("Voting is closed. Finish time: " + voteFinishTime);
        }
        Vote newVote = voteRepository.findByUserAndDate(user, localDate);
        if (newVote == null) {
            newVote = new Vote(restaurant, user, localDate);
        } else {
            newVote.setRestaurant(restaurant);
        }
        voteRepository.save(newVote);
    }

    public Vote get(User user) {
        return get(user, LocalDate.now());
    }

    public Vote get(User user, LocalDate date) {
        return voteRepository.findByUserAndDate(user, date);
    }
}
