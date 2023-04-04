package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.error.VotingClosedException;
import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.repository.UserRepository;
import com.ptitsyn.restvote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Value("${app.voteFinishTime}")
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime voteFinishTime;

    //    @CacheEvict(value = "votes", allEntries = true)
    public Vote createNow(User user, int restaurantId) throws VotingClosedException {
        LocalDateTime now = LocalDateTime.now();
        return voteRepository.save(new Vote(restaurantRepository.getReferenceById(restaurantId),
                userRepository.getReferenceById(user.id()), now.toLocalDate(), now.toLocalTime()));
    }

    public void updateNow(User user, int restaurantId) throws VotingClosedException {
        LocalDateTime now = LocalDateTime.now();
        if (now.toLocalTime().isAfter(voteFinishTime)) {
            throw new VotingClosedException("Voting is closed at " + voteFinishTime.toString());
        }
        voteRepository.updateRestaurantAndCastedTimeByCastedDateAndUser(restaurantRepository.getReferenceById(restaurantId), now.toLocalTime(), now.toLocalDate(), user);
    }
}
