package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.Vote;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
public interface VoteRepository extends BaseRepository<Vote> {
    Vote findFirstByUser_IdAndCastedBetweenOrderByCastedDesc(@NonNull Integer id, @NonNull LocalDateTime castedStart, @NonNull LocalDateTime castedEnd);

}
