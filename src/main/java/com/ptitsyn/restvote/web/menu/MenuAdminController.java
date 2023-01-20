package com.ptitsyn.restvote.web.menu;

import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = com.ptitsyn.restvote.web.menu.MenuAdminController.REST_URL, produces =
        MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("ROLE_ADMIN")
public class MenuAdminController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menus";

    MenuService menuService;

    @GetMapping("/{date}")
    public Menu get(@PathVariable int restaurantId,
                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return menuService.get(restaurantId, date);
    }

    @PutMapping("/{date}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable int restaurantId,
                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody Menu menu) {
        log.info("update {} menu at date {}", restaurantId, date);
        menuService.update(restaurantId, date, menu);
    }

}
