package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.error.DataConflictException;
import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.util.validation.ValidationUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static com.ptitsyn.restvote.util.validation.ValidationUtil.*;

@Service
@Validated
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public void update(@Valid Restaurant restaurant) {
        ValidationUtil.assureIdConsistent(restaurant, getById(restaurant.id()).id());
        try {
            checkModification(repository.update(restaurant.id(), restaurant.getName()), restaurant.id());
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException("Data conflict", e);
        }
    }

    public Restaurant getById(int id) {
        return get(id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Restaurant create(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "Restaurant should not be null");
        checkNew(restaurant);
        try {
            return repository.save(restaurant);
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException("A restaurant with the same name already exists", e);
        }
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public List<Restaurant> getAllWithMenuForToday() {
        return repository.findAll(withTodaysMenu());
    }

    public static Specification<Restaurant> withTodaysMenu() {
        return (root, query, builder) -> {
            Join<Restaurant, Menu> menuJoin = root.join("menus", JoinType.LEFT);
            return builder.equal(menuJoin.get("date"), builder.currentDate());
        };
    }
}
