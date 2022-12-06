package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Vote.UserDatePK> {

    List<Vote> getAllByDate(LocalDate date);
}