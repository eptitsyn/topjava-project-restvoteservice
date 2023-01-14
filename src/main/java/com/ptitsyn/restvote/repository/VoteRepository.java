package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.model.Vote;
import com.ptitsyn.restvote.to.VoteCountTo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    Vote findFirstByUser_IdAndCastedBetweenOrderByCastedDesc(@NonNull Integer id, @NonNull LocalDateTime castedStart,
                                                             @NonNull LocalDateTime castedEnd);


    @EntityGraph(attributePaths = {"restaurant", "user"})
    @Query("SELECT v FROM Vote v " +
            "WHERE v.id = (" +
            "SELECT MAX(vv.id) FROM Vote vv WHERE vv.casted BETWEEN :startDate AND :endDate GROUP BY vv.restaurant)")
    List<Vote> findLastVotesForDate(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);


    @EntityGraph(attributePaths = {"restaurant", "user"})
    @Query("SELECT v FROM Vote v " +
            "WHERE v.id = (" +
            "SELECT MAX(vv.id) FROM Vote vv WHERE vv.user = :user AND vv.casted BETWEEN :startDate AND :endDate)")
    Vote findLastVoteForUserForDate(@Param("user") User user, @Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);

    //    @EntityGraph(attributePaths = {"restaurant"})
    @Query("SELECT new com.ptitsyn.restvote.to.VoteCountTo(v.restaurant, COUNT(v) as voteCount) " +
            "FROM Vote v " +
            "JOIN v.restaurant r " +
            "WHERE v.id IN (" +
            "SELECT MAX(vv.id) FROM Vote vv GROUP BY vv.user" +
            ") " +
            "GROUP BY v.restaurant ORDER BY voteCount DESC")
    List<VoteCountTo> findAllResultByLastVote(@Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate);

    @NonNull
    @EntityGraph(attributePaths = {"restaurant"})
    Vote save(@NonNull Vote vote);
}
