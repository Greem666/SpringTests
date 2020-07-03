package com.springtraining.hibernate.manytomany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "COMPANIES")
public class Company {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "COMPANY_ID")
    private int id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "companies")
    private List<Employee> employees = new ArrayList<>();

    public Company(@NotNull String name) {
        this.name = name;
    }
}
