package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.Vote;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VoteRepository extends BaseRepository<Vote> {

//    Vote findByUserAndCasted_Date(@NotNull User user, LocalDate casted_date);
}
