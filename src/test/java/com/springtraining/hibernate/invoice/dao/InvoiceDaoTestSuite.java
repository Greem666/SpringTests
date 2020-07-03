package com.springtraining.hibernate.invoice.dao;

import com.springtraining.hibernate.invoice.Invoice;
import com.springtraining.hibernate.invoice.Item;
import com.springtraining.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {
    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private ItemDao itemDao;

    @Test
    public void testInvoiceDaoSave() {
        // Given
        Product product1 = new Product("prod1");
        Product product2 = new Product("prod2");

        Item item1 = new Item(product1, "100", 10);
        Item item2 = new Item(product1, "200", 10);
        Item item3 = new Item(product2, "50", 2);
        Item item4 = new Item(product2, "250", 25);

        Invoice invoice1 = new Invoice("HK-47");
        item2.setInvoice(invoice1);
        item3.setInvoice(invoice1);

        Invoice invoice2 = new Invoice("HK-48");
        item1.setInvoice(invoice2);
        item4.setInvoice(invoice2);

        // When
        invoiceDao.save(invoice1);
        invoiceDao.save(invoice2);

        int invoice1_id = invoice1.getId();
        int invoice2_id = invoice2.getId();

        // Then
        Assert.assertNotEquals(0, invoice1_id);
        Assert.assertNotEquals(0, invoice2_id);

        Assert.assertTrue(invoice1.getItems().containsAll(Arrays.asList(item2, item3)));
        Assert.assertTrue(invoice2.getItems().containsAll(Arrays.asList(item1, item4)));

        Assert.assertTrue(product1.getItems().containsAll(Arrays.asList(item1, item2)));
        Assert.assertTrue(product1.getItems().containsAll(Arrays.asList(item3, item4)));

        // Clean-up
        try {
            invoiceDao.deleteById(invoice1_id);
            invoiceDao.deleteById(invoice2_id);
        } catch (Exception e) {
            // Do nothing
        }
    }
}
