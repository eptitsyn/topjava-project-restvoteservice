package com.ptitsyn.restvote.web.menu;

import com.ptitsyn.restvote.service.MenuService;
import com.ptitsyn.restvote.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.ptitsyn.restvote.web.RestaurantTestData.RESTAURANT_MATCHER;
import static com.ptitsyn.restvote.web.RestaurantTestData.restaurant1;
import static com.ptitsyn.restvote.web.TestUtil.userHttpBasic;
import static com.ptitsyn.restvote.web.user.UserTestData.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuControllerTest extends AbstractControllerTest {

    public static final String REST_URL = MenuController.REST_URL + '/';

    @Autowired
    private MenuService menuService;

    @Test
    void get() throws Exception {
        String urlTemplate = REST_URL.replace("{restaurantId}", String.valueOf(restaurant1.id())) + "/menus/" + LocalDate.now();
        perform(MockMvcRequestBuilders.get(urlTemplate)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurant1));
    }
}
