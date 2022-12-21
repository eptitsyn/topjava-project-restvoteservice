package com.ptitsyn.restvote.repository;

import com.ptitsyn.restvote.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    //@EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAll();

    //@EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Optional<Restaurant> getWithMenu(int id);

    @EntityGraph(value = "graph.RestaurantWithTodayMenu", type = EntityGraph.EntityGraphType.FETCH)
    List<Restaurant> findAll(Specification<Restaurant> specification);

    @Modifying
    @Query("UPDATE Restaurant r SET r.name=:name WHERE r.id=:id")
    int update(@Param("id") int id, @Param("name") String name);
}
