package com.ptitsyn.restvote.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(name = "UniqueNumberAndStatus", columnNames = {
        "name"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(name = "graph.RestaurantWithTodayMenu", attributeNodes = {
        @NamedAttributeNode("menus")
})
public class Restaurant extends NamedEntity {

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set<Menu> menus;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
