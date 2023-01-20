package com.ptitsyn.restvote.web.menu;

import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.service.MenuService;
import com.ptitsyn.restvote.service.RestaurantService;
import com.ptitsyn.restvote.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces =
        MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Secured("ROLE_ADMIN")
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menus";
    private final RestaurantRepository restaurantRepository;
    MenuService menuService;
    RestaurantService restaurantService;

    @GetMapping("/{date}")
    public Menu get(@PathVariable int restaurantId,
                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return menuService.get(restaurantId, date);
    }

    @DeleteMapping("/{date}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @AuthenticationPrincipal AuthUser authUser,
                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Admin {} deleting restaurant {}", authUser.getUsername(), restaurantId);
        menuService.delete(restaurantId, date);
    }

    @PutMapping("/{date}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable int restaurantId,
                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody Menu menu) {
        log.info("update {} menu at date {}", restaurantId, date);
        menuService.update(restaurantId, date, menu);
    }

    @PostMapping("/{date}")
    ResponseEntity<Menu> create(@PathVariable int restaurantId, @Valid @RequestBody Menu menu,
                                @AuthenticationPrincipal AuthUser authUser,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Admin {} creating menu {}", authUser.getUsername(), menu);
        Menu created = menuService.create(menu);
        menu.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        menu.setDate(date);
        URI uriOfNewResource =
                ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL + "/{date}").buildAndExpand(created.getDate()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
