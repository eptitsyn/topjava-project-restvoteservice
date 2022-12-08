package com.ptitsyn.restvote.to;

import com.ptitsyn.restvote.model.Menu;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {
    //TODO get rid of TO

    private Menu todayMenu;

    public RestaurantTo(Integer id, String name, Menu todayMenu) {
        super(id, name);
        this.todayMenu = todayMenu;
    }
}
