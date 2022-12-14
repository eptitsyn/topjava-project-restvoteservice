package com.ptitsyn.restvote.service;

import com.ptitsyn.restvote.error.NotFoundException;
import com.ptitsyn.restvote.web.RestaurantTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ptitsyn.restvote.util.validation.ValidationUtil.getRootCause;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    void delete() {
        service.delete(RestaurantTestData.restaurant1.id());
        assertThrows(NotFoundException.class, () -> {
            service.get(RestaurantTestData.restaurant1.id());
        });
    }

    @Test
    void getAllWithMenu() {
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