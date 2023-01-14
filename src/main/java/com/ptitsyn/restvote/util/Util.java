package com.ptitsyn.restvote.util;

import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.model.Restaurant;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@UtilityClass
public class Util {

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

    public static Specification<Restaurant> withTodaysMenu() {
        return (root, query, builder) -> {
            Join<Restaurant, Menu> menuJoin = root.join("menus", JoinType.LEFT);
            return builder.equal(menuJoin.get("date"), builder.currentDate());
        };
    }
}
