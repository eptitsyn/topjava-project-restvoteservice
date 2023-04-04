package com.ptitsyn.restvote.web.menu;

import com.ptitsyn.restvote.model.Menu;
import com.ptitsyn.restvote.model.MenuItem;
import com.ptitsyn.restvote.web.MatcherFactory;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static com.ptitsyn.restvote.web.restaurant.RestaurantTestData.restaurant1;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    //    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingEqualsComparator(Menu.class);
    public static final Menu menu1_today = new Menu(restaurant1, LocalDate.now(), new LinkedList<>(List.of(new MenuItem("Репа паренная", 12), new MenuItem("Борщ", 34))));
    public static MatcherFactory.Matcher<Menu> MENU_MATCHER =
            MatcherFactory.usingAssertions(Menu.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
}
