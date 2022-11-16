package ru.javaops.topjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.javaops.topjava.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "menu_unique_restaurant_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Menu extends BaseEntity{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @NoHtml
    private String menu;
}
