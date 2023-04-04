package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.model.Vote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Transactional
    @Modifying
    @Query("""
            update Vote v set v.restaurant = :restaurant, v.castedTime = :castedTime
            where v.castedDate = :castedDate and v.user = :user""")
    int updateRestaurantAndCastedTimeByCastedDateAndUser(@NonNull @Param("restaurant") Restaurant restaurant, @NonNull @Param("castedTime") LocalTime castedTime, @NonNull @Param("castedDate") LocalDate castedDate, @NonNull @Param("user") User user);

    List<Vote> findByCastedDate(LocalDate castedDate);

    Vote findByUserAndCastedDate(User user, LocalDate castedDate);
}
