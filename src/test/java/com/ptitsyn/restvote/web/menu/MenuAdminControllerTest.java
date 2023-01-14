package com.ptitsyn.restvote.web.menu;

import com.ptitsyn.restvote.service.MenuService;
import com.ptitsyn.restvote.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.ptitsyn.restvote.web.TestUtil.userHttpBasic;
import static com.ptitsyn.restvote.web.menu.MenuTestData.MENU_MATCHER;
import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.NON_EXISTING_ID;
import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.restaurant1;
import static com.ptitsyn.restvote.web.user.UserTestData.admin;
import static com.ptitsyn.restvote.web.user.UserTestData.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuAdminControllerTest extends AbstractControllerTest {

    public static final String REST_URL = MenuAdminController.REST_URL + '/';

    @Autowired
    private MenuService menuService;

    @Test
    void get() throws Exception {
        String urlTemplate = getUrlTemplate(restaurant1.id());
        perform(MockMvcRequestBuilders.get(urlTemplate)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MenuTestData.menu1_today));
    }

    private static String getUrlTemplate(int id) {
        return REST_URL.replace("{restaurantId}", String.valueOf(id)) + LocalDate.now();
    }

    @Test
    void getNotExistingId() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrlTemplate(NON_EXISTING_ID))
                .with(userHttpBasic(admin)))
                .andExpect(status().isGone())
                .andDo(print());
    }

    @Test
    void getWithNoAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrlTemplate(restaurant1.id())))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void getWithUserAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrlTemplate(restaurant1.id()))
                .with(userHttpBasic(user)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }


}
