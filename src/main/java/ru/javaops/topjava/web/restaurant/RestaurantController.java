package ru.javaops.topjava.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.web.AuthUser;

import java.util.List;

@RestController
@RequestMapping(value = ru.javaops.topjava.web.restaurant.RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return repository.getAllWithMenu();
    }
}

