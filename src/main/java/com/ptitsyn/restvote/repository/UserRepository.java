package com.ptitsyn.restvote.repository;

import org.springframework.transaction.annotation.Transactional;
import com.ptitsyn.restvote.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);
}