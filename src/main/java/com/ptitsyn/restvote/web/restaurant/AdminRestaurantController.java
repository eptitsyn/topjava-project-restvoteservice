package com.ptitsyn.restvote.web.restaurant;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.service.RestaurantService;
import com.ptitsyn.restvote.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.ptitsyn.restvote.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("ROLE_ADMIN")
public class AdminRestaurantController {

    static final String REST_URL = "/api/admin/restaurants";
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("Admin {} requested list of all restaurants", authUser.getUsername());
        return restaurantRepository.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("Admin {} requested restaurant with id {}", authUser.getUsername(), id);
        return restaurantService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("Admin {} deleting restaurant {}", authUser.getUsername(), id);
        restaurantService.delete(id);
    }

    @PostMapping
    ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant,
                                      @AuthenticationPrincipal AuthUser authUser) {
        log.info("Admin {} creating {}", authUser.getUsername(), restaurant);
        Restaurant created = restaurantService.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id,
                @AuthenticationPrincipal AuthUser authUser) {
        log.info("Admin {} updating {}", authUser.getUsername(), restaurant);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }
}
