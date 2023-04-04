package com.ptitsyn.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class VoteServiceTest {

    private static final LocalDateTime FIXED_TIME = LocalDateTime.of(2022, 1, 1, 4, 30, 0);

    @Autowired
    private VoteService service;
    
}
