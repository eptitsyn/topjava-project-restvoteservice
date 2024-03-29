package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.model.Restaurant;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    void deleteByRestaurantAndDate(@NonNull Restaurant restaurant, @NonNull LocalDate date);

    List<Menu> findByRestaurantOrderByDateAsc(@NonNull Restaurant restaurant);

    Menu findMenuByRestaurantAndDate(@NotNull Restaurant restaurant, @NotNull LocalDate date);
}
