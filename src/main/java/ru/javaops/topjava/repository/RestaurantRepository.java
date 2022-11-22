package ru.javaops.topjava.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional
public interface RestaurantRepository extends BaseRepository<Restaurant>{

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAllWithMenu();

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Optional<Restaurant> getWithMenu(int id);
}
