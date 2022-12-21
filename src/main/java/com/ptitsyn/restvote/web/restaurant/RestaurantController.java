package com.ptitsyn.restvote.web.restaurant;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.service.RestaurantService;
import com.ptitsyn.restvote.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ptitsyn.restvote.web.restaurant.RestaurantController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestaurantController {

    static final String REST_URL = "/api/restaurants";

    private final RestaurantService service;

    @GetMapping
    public List<Restaurant> getAllWithMenuForToday(@AuthenticationPrincipal AuthUser authUser) {
        log.info("User {} requested restaurant list with menu for today", authUser.getUsername());
        return service.getAllWithMenuForToday();
    }
}
