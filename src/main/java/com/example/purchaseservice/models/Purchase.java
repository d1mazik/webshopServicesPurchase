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
    //@Transient
    private Long customerId;
    //@Transient
    private Long itemsId;


    public Purchase(Date dateOfPurchase, Long customerId, Long itemsId) {
        this.dateOfPurchase = dateOfPurchase;
        this.customerId = customerId;
        this.itemsId=itemsId;
    }
}
