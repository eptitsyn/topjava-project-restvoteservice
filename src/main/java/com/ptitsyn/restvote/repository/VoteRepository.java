package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.model.Vote;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
public interface VoteRepository extends BaseRepository<Vote> {

    Vote findByUserAndDate(@NonNull User user, @NonNull LocalDate date);
}