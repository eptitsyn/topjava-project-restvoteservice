package com.ptitsyn.restvote.web.user;

import com.ptitsyn.restvote.model.Role;
import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.util.JsonUtil;
import com.ptitsyn.restvote.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {

    public static final MatcherFactory.Matcher<User> USER_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");
    public static final int NOT_FOUND = 100;
    public static final User user = new User(1, "User", "user@yandex.ru", "password", Role.USER);
    public static final User admin = new User(2, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
    public static final User user2 = new User(3, "User2", "user2@gmail.com", "user");
    public static final User user3 = new User(4, "User3", "user3@gmail.com", "user");
    public static final List<User> users = List.of(user, admin, user2, user3);
    public static final List<User> usersSorted = List.of(admin, user, user2, user3);

    public static MatcherFactory.Matcher<User> USER_WITH_MEALS_MATCHER =
            MatcherFactory.usingAssertions(User.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "meals.user", "password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(1, "UpdatedName", "user@yandex.ru", "newPass", false, new Date(), List.of(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
