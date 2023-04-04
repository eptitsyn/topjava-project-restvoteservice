package com.ptitsyn.restvote.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NamedEntityGraph(name = "Vote.restaurant", attributeNodes = @NamedAttributeNode("restaurant"))
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "casted_date"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Vote extends BaseEntity {

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "casted_date", nullable = false)
    @NotNull
    private LocalDate castedDate;

    @Column(name = "casted_time", nullable = false)
    @NotNull
    private LocalTime castedTime;
}
