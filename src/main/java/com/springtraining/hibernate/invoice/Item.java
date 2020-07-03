package com.springtraining.hibernate.invoice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@Table(name="ITEMS")
public class Item {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;

    @NotNull
    @Column(name = "VALUE")
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name="INVOICE_ID")
    private Invoice invoice;

    public Item(Product product, String price, int quantity) {
        this.product = product;
        this.product.getItems().add(this);

        this.price = new BigDecimal(price);
        this.quantity = quantity;
        this.value = this.price.multiply(new BigDecimal(quantity));
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        invoice.getItems().add(this);
    }
}
