package org.sid.ebanckingbackend.repositories;

import org.sid.ebanckingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustormerRepository extends JpaRepository<Customer,Long> {
 @Query("select c from Customer c where c.name like :kw")
 List<Customer> searchCustomer(@Param(value="kw") String keyword);
}
