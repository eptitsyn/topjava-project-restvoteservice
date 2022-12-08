package com.ptitsyn.restvote.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {

//    @Serial
//    private static final long serialVersionUID = 1L;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OrderBy("date DESC")
//    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
//    @Schema(hidden = true)
//    private List<Menu> menus;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}

