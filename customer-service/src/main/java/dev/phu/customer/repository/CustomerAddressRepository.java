package dev.phu.customer.repository;

import dev.phu.customer.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerAddressRepository extends JpaRepository<Address, UUID> {

    /**
     * Retrieve all addresses for a specific customer.
     *
     * @param customerId the UUID of the customer
     * @return a list of addresses for the customer
     */
    List<Address> findByCustomerId(UUID customerId);

    /**
     * Retrieve the default address for a specific customer.
     *
     * @param customerId the UUID of the customer
     * @return an Optional containing the default address, if it exists
     */
    @Query("SELECT a FROM Address a WHERE a.customer.id = :customerId AND a.isDefault = true")
    Optional<Address> findDefaultByCustomerId(@Param("customerId") UUID customerId);

    /**
     * Delete a specific address by its ID.
     *
     * @param id the UUID of the address to delete
     */
    void deleteById(UUID id);
}
