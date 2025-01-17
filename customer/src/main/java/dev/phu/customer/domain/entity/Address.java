package dev.phu.customer.domain.entity;

import dev.phu.customer.domain.aggregate.Customer;
import dev.phu.customer.domain.vo.AddressVO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Embedded
    private AddressVO addressDetails;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private boolean isDefault;

}
