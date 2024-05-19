package org.sid.ebanckingbackend.repositories;

import org.sid.ebanckingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustormerRepository extends JpaRepository<Customer,Long> {

}
