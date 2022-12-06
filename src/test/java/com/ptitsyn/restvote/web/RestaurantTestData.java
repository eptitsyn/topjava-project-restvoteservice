package com.ptitsyn.restvote.web;

import com.ptitsyn.restvote.model.Restaurant;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final Restaurant restaurant1 = new Restaurant(1, "Shinok");
    public static final Restaurant restaurant2 = new Restaurant(2, "Bochka");
    public static final Restaurant restaurant3 = new Restaurant(3, "Manon");

}
