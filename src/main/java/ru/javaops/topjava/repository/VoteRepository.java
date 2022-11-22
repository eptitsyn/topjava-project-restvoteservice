package ru.javaops.topjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaops.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Vote.UserDatePK> {

    List<Vote> getAllByDate(LocalDate date);
}