package ru.javaops.topjava.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.topjava.model.Menu;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    private Menu todayMenu;

    public RestaurantTo(Integer id, String name, Menu todayMenu) {
        super(id, name);
        this.todayMenu = todayMenu;
    }
}
