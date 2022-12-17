package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.ptitsyn.restvote.util.Util.withTodaysMenu;
import static com.ptitsyn.restvote.util.validation.ValidationUtil.checkNew;
import static com.ptitsyn.restvote.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(@NonNull Restaurant restaurant, int id) {
        checkNotFoundWithId(repository.save(restaurant), id);
    }

    public Restaurant create(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "Restaurant should not be null");
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    public List<Restaurant> getAllWithTodayMenu() {
        return repository.findAll(withTodaysMenu());
    }
}
