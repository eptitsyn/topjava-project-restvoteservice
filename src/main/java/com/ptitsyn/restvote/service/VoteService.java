package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.repository.VoteRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.Clock;
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

//    public void vote(@NonNull User user, int restaurantId) {
//        vote(user, restaurantRepository.getReferenceById(restaurantId), LocalDateTime.now(clock));
//    }
//
//    public void vote(User user, Restaurant restaurant, LocalDateTime localDateTime) {
//        if (localDateTime.toLocalTime().isAfter(voteFinishTime)) {
//            throw new IllegalStateException("Voting is closed. Finish time: " + voteFinishTime);
//        }
//        Vote newVote = voteRepository.findByUserAndCastedDate(user, localDateTime.toLocalDate());
//        if (newVote == null) {
//            newVote = new Vote(restaurant, user, localDateTime);
//        } else {
//            newVote.setRestaurant(restaurant);
//        }
//        voteRepository.save(newVote);
//    }

//    public Vote get(User user) {
//        return get(user, LocalDate.now());
//    }
//
//    public Vote get(User user, LocalDate date) {
//        return voteRepository.findByUserAndCastedDate(user, date);
//    }
}
