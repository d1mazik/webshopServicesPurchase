package com.example.purchaseservice.repos;


import com.example.purchaseservice.DTO.Customer;
import com.example.purchaseservice.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {
   /* @Query("SELECT p FROM Purchase p WHERE p.customerId = :customerId")
    List<Purchase> findByCustomerId(@Param("customerId") Long customerId);*/
    //List<Purchase> findbyCustomer(Customer customer);
}
