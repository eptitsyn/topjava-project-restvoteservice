package com.ptitsyn.restvote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptitsyn.restvote.util.convertor.HashMapConverter;
import com.ptitsyn.restvote.util.validation.NoHtml;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.LinkedList;


@Entity

@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(name = "UniqueRestaurantAndDate", columnNames = {"restaurant_id", "date"})})
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Menu extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    //TODO rename
    private LocalDate date;

    @Column(name = "menu", nullable = false)
    @NotBlank
    @NoHtml
    @Convert(converter = HashMapConverter.class)
    private LinkedList<Item> menu;
}
