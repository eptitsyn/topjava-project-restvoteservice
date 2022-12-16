package com.ptitsyn.restvote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptitsyn.restvote.util.convertor.HashMapConverter;
import com.ptitsyn.restvote.util.validation.NoHtml;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;


@Entity
@Table(name = "menu")
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(Menu.RestaurantDatePK.class)
@ToString(callSuper = true)
public class Menu {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Id
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    @Id
    private LocalDate date;

    @Column(name = "dishes", nullable = false)
    @NotBlank
    @NoHtml
    @Convert(converter = HashMapConverter.class)
    private LinkedList<Dish> dishes;

    @EqualsAndHashCode
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RestaurantDatePK implements Serializable {

        private Integer restaurant;
        private LocalDate date;
    }
}
