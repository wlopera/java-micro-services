package wlopera.lab.store.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wlopera.lab.store.shopping.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {
}