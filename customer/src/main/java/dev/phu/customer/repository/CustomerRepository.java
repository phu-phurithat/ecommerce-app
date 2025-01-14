package dev.phu.customer.repository;

import dev.phu.customer.domain.aggregate.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
