package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ptitsyn.restvote.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu();
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public void update(@NonNull Restaurant restaurant) {
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    ;


}
