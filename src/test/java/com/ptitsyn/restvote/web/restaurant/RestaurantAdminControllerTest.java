package com.ptitsyn.restvote.web.restaurant;

import com.ptitsyn.restvote.error.NotFoundException;
import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.service.RestaurantService;
import com.ptitsyn.restvote.util.JsonUtil;
import com.ptitsyn.restvote.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.ptitsyn.restvote.web.TestUtil.userHttpBasic;
import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.*;
import static com.ptitsyn.restvote.web.user.UserTestData.admin;
import static com.ptitsyn.restvote.web.user.UserTestData.user;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantAdminControllerTest extends AbstractControllerTest {

    public static final String REST_URL = RestaurantAdminController.REST_URL + '/';

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
    void get() throws Exception {
        for (Restaurant restaurant : restaurants) {
            perform(MockMvcRequestBuilders.get(REST_URL + restaurant.id())
                    .with(userHttpBasic(admin)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(RESTAURANT_MATCHER.contentJson(restaurant));
        }
    }

    @Test
    void getNotExisting() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 99999)
                .with(userHttpBasic(admin)))
                .andExpect(status().isGone());
    }

    @Test
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(newRestaurant)));

        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + updated.id())
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantService.get(updated.id()), updated);
    }

    @Test
    void updateWithExistingName() throws Exception {
        Restaurant updated = getUpdated();
        updated.setName(restaurant3.getName());
        perform(MockMvcRequestBuilders.put(REST_URL + updated.id()).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isConflict());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + restaurant1.id())
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(restaurant1.id()));
    }

    @Test
    void getWithNoAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + restaurant1.id()))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void getWirhUserAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + restaurant1.id())
                .with(userHttpBasic(user)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }
}
