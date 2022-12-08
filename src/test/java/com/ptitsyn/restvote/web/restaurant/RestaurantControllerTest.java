package com.ptitsyn.restvote.web.restaurant;

import com.ptitsyn.restvote.service.RestaurantService;
import com.ptitsyn.restvote.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.ptitsyn.restvote.web.RestaurantTestData.RESTAURANT_MATCHER;
import static com.ptitsyn.restvote.web.RestaurantTestData.restaurant1;
import static com.ptitsyn.restvote.web.TestUtil.userHttpBasic;
import static com.ptitsyn.restvote.web.user.UserTestData.admin;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest extends AbstractControllerTest {

    public static final String REST_URL = RestaurantController.REST_URL + '/';

    @Autowired
    private RestaurantService restaurantService;

//    @Test
//    void getAll() {
//        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.restaurant1.id()))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MEAL_MATCHER.contentJson(meal1));
//    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + restaurant1.id())
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurant1));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}