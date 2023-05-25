package com.example.purchaseservice.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;
    private String fullName;
    private String SSN;

    public Customer(String fullName, String SSN) {
        this.fullName = fullName;
        this.SSN = SSN;
    }

}
