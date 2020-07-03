package com.springtraining.hibernate.invoice.dao;

import com.springtraining.hibernate.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InvoiceDao extends CrudRepository<Invoice, Integer> {
}
