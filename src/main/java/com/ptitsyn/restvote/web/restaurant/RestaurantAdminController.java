package com.ptitsyn.restvote.web.restaurant;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.ptitsyn.restvote.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = com.ptitsyn.restvote.web.restaurant.RestaurantAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("ROLE_ADMIN")
public class RestaurantAdminController {

    static final String REST_URL = "/api/admin/restaurants";

    RestaurantService service;

    @PostMapping
    ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
        service.update(restaurant, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id);
    }
}
