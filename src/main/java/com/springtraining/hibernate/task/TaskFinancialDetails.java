package com.springtraining.hibernate.task;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Table(name="TASK_FINANCIAL_DETAILS")
public class TaskFinancialDetails {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID")
    private int id;

    @NotNull
    @Column(name="PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name="PAID")
    private boolean paid;

    public TaskFinancialDetails(BigDecimal price, boolean paid) {
        this.price = price;
        this.paid = paid;
    }
}
