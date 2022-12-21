package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.error.DataConflictException;
import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import lombok.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.ptitsyn.restvote.util.Util.withTodaysMenu;
import static com.ptitsyn.restvote.util.validation.ValidationUtil.*;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(@NonNull Restaurant restaurant) {
//        checkNotFoundWithId(repository.save(restaurant), id);
//        ValidationUtil.assureIdConsistent(restaurant, getById(restaurant.id()).orElseThrow().id());
//        if (StringUtils.isEmpty(restaurant.getName())) {
//            throw new IllegalRequestDataException("Restaurant name cannot be empty");
//        }
        checkModification(repository.update(restaurant.id(), restaurant.getName()), restaurant.getId());
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

    public Restaurant getById(int id) {
        return get(id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }
}
