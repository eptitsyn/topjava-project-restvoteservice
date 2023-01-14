package com.ptitsyn.restvote.web.restaurant;

import com.ptitsyn.restvote.service.RestaurantService;
import com.ptitsyn.restvote.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.ptitsyn.restvote.web.TestUtil.userHttpBasic;
import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.*;
import static com.ptitsyn.restvote.web.user.UserTestData.admin;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest extends AbstractControllerTest {

    public static final String REST_URL = RestaurantController.REST_URL + '/';

    @Autowired
    private RestaurantService restaurantService;


    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurants));
    }

    @Test
    void getNonExistentRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NON_EXISTING_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNotFound());
    }
}
