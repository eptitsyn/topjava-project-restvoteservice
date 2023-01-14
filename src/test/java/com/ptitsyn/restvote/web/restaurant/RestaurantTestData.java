package com.ptitsyn.restvote.web.restaurant;

import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static final int NON_EXISTING_ID = 9999;
    public static final Restaurant restaurant1 = new Restaurant(1, "Manon");
    public static final Restaurant restaurant2 = new Restaurant(2, "Pushkin");
    public static final Restaurant restaurant3 = new Restaurant(3, "Shinok");
    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3);

    public static Restaurant getNew() {
        return new Restaurant(null, "NewRestaurant");
    }

    public static Restaurant dropId(Restaurant r) {
        return new Restaurant(null, r.getName());
    }

    public static Restaurant getUpdated() {
        return new Restaurant(restaurant2.id(), "New" + restaurant2.getName());
    }
}
