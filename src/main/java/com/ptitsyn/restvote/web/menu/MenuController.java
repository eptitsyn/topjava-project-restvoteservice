package com.ptitsyn.restvote.web.menu;

import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = com.ptitsyn.restvote.web.menu.MenuController.REST_URL, produces =
        MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class MenuController {

    static final String REST_URL = "/api/restaurants/{restaurantId}/menus";

    MenuService menuService;

    @GetMapping
    public List<Menu> getAll(@PathVariable int restaurantId) {
        return menuService.getAll(restaurantId);
    }


}
