package com.ptitsyn.restvote.web.menu;

import com.ptitsyn.restvote.model.Dish;
import com.ptitsyn.restvote.service.MenuService;
import com.ptitsyn.restvote.util.convertor.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = com.ptitsyn.restvote.web.menu.MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class MenuAdminController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menus";

    MenuService menuService;

    @PutMapping("/{date}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable int restaurantId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("update {} menu at date {}", restaurantId, date);
        HashMapConverter hm = new HashMapConverter();
        log.error(hm.convertToDatabaseColumn(new LinkedList<>(List.of(new Dish("asd", 12)))));
        //assureIdConsistent(restaurant, id);
        menuService.update(restaurantId, date);
    }

}