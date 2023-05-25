package com.example.purchaseservice.models;


import com.example.purchaseservice.DTO.Customer;
import com.example.purchaseservice.DTO.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateOfPurchase;
    @Transient
    private Customer customer;
    @Transient
    private List<Item> itemsList;


    public Purchase(Date dateOfPurchase, Customer customer, List<Item> itemsList) {
        this.dateOfPurchase = dateOfPurchase;
        this.customer = customer;
        this.itemsList=itemsList;
    }
}
