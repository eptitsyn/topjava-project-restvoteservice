package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.error.DataConflictException;
import com.ptitsyn.restvote.error.ErrorConstants;
import com.ptitsyn.restvote.error.IllegalRequestDataException;
import com.ptitsyn.restvote.error.NotFoundException;
import com.ptitsyn.restvote.model.Restaurant;
import com.ptitsyn.restvote.repository.RestaurantRepository;
import com.ptitsyn.restvote.web.restaurant.RestaurantTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.ptitsyn.restvote.util.validation.ValidationUtil.getRootCause;
import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.NON_EXISTING_ID;
import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.restaurant3;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void createWithEmptyName() {
        Restaurant invalid = new Restaurant(null, "");
        assertThrows(ConstraintViolationException.class, () -> service.create(invalid));
    }

    @Test
    public void createWithId() {
        Restaurant invalid = new Restaurant(1, "name");
        assertThrows(IllegalRequestDataException.class, () -> service.create(invalid));
    }

    @Test
    void createWithExistingName() {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setName(RestaurantTestData.restaurant1.getName());
        DataConflictException ex = assertThrows(DataConflictException.class, () -> service.create(newRestaurant));
//        assertThat(ex.getOptions().includes(ErrorAttributeOptions.Include.MESSAGE)).isTrue();
        assertThat(ex.getMessage()).isEqualTo("A restaurant with the same name already exists");
    }

    @Test
    void create() {
        Restaurant created = service.create(RestaurantTestData.getNew());
        Restaurant found = service.get(created.id());
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(found, created);
    }

    @Test
    void update() {
        Restaurant created = service.create(RestaurantTestData.getNew());
        Restaurant updated = RestaurantTestData.getUpdated();
        updated.setId(created.getId());
        service.update(updated);
        Restaurant found = service.get(created.id());
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(found, updated);
    }

    @Test
    void updateWithEmptyName() {
        Restaurant updated = RestaurantTestData.getUpdated();
        updated.setName("");
        ConstraintViolationException ex = assertThrows(ConstraintViolationException.class,
                () -> service.update(updated));
        assertThat(ex.getConstraintViolations().size()).isEqualTo(2);
        assertThat(ex.getConstraintViolations().stream().anyMatch((e) -> e.getMessage().equals(ErrorConstants.NAME_IS_MANDATORY))).isTrue();
    }

    @Test
    void updateWithExistingName() {
        Restaurant restaurant = RestaurantTestData.getNew();
        service.create(restaurant);
        Restaurant updated = RestaurantTestData.getUpdated();
        updated.setName(restaurant3.getName());
        assertThrows(DataConflictException.class, () -> service.update(updated));
    }


    @Test
    void updateWithInvalidId() {
        assertThrows(NotFoundException.class, () -> service.update(new Restaurant(NON_EXISTING_ID, "Updated" +
                                                                                                   " Name")));
    }

    @Test
    void deleteWithInvalidId() {
        assertThrows(NotFoundException.class, () -> service.delete(NON_EXISTING_ID));
    }

    @Test
    void getWithInvalidId() {
        assertThrows(NotFoundException.class, () -> service.get(NON_EXISTING_ID));
    }

    @Test
    void delete() {
        Restaurant created = service.create(RestaurantTestData.getNew());
        service.delete(created.id());
        assertThrows(NotFoundException.class, () -> service.get(created.id()));
    }

    @Test
    void get() {
        Restaurant created = service.create(RestaurantTestData.getNew());
        Restaurant found = service.get(created.id());
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(found, created);
    }

    @Test
    void getAll() {
        List<Restaurant> all = restaurantRepository.getAll();
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(all, RestaurantTestData.restaurants);
    }

    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    private <T extends Throwable> void validateRootCause(Class<T> rootExceptionClass, Runnable runnable) {
        assertThrows(rootExceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}
