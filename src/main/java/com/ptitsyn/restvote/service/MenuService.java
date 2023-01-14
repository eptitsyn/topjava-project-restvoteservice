package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.model.Dish;
import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.repository.MenuRepository;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static com.ptitsyn.restvote.util.validation.ValidationUtil.checkNotFound;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository,
                       RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu get(int restaurantId, LocalDate date) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        return checkNotFound(menuRepository.findMenuByRestaurantAndDate(restaurant, date), "Menu not found");
    }

    public List<Menu> getAll(int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        return menuRepository.findByRestaurantOrderByDateAsc(restaurant);
    }

    @CacheEvict(value = "menu", allEntries = true)
    public void update(int restaurantId, LocalDate date) {
        //todo
        menuRepository.save(new Menu(restaurantRepository.getReferenceById(restaurantId), date,
                new LinkedList<>(List.of(new Dish("asd", 12)))));
    }
}
