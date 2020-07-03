package com.springtraining.hibernate.invoice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@Table(name="INVOICES")
public class Invoice {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "NUMBER")
    private String number;

    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Item> items = new ArrayList<>();

    public Invoice(@NotNull String number) {
        this.number = number;
    }
}
