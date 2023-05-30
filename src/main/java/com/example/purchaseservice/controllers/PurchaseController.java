package com.example.purchaseservice.controllers;

import com.example.purchaseservice.models.Purchase;
import com.example.purchaseservice.DTO.Customer;
import com.example.purchaseservice.DTO.Item;
import com.example.purchaseservice.repos.PurchaseRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseRepo purchaseRepo;

    /*
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    private RestTemplate restTemplate;*/
    private RestTemplate restTemplate=new RestTemplate();
    public PurchaseController( PurchaseRepo purchaseRepo) {

        this.purchaseRepo = purchaseRepo;
    }

    @GetMapping("")
    public List<Purchase> getAllPurchases() {
        return purchaseRepo.findAll();
    }
    @RequestMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id){
        return purchaseRepo.findById(id).get();
    }
    @RequestMapping("/delete/{id}")
    public List<Purchase> deleteById(@PathVariable Long id){
        purchaseRepo.deleteById(id);
        return purchaseRepo.findAll();
    }

    @Value("${item-service.baseurl}")
    private String itemServiceBaseUrl;
    @Value("${customer-service.baseurl}")
    private String customerServiceBaseUrl;

    @PostMapping("/newbypost/{customerid}/{itemid}")
    public String newPurchaseByPost(@PathVariable Long customerid,@PathVariable Long itemid ){
        String CostumerServiceUr1=customerServiceBaseUrl+"/customers/"+customerid;
        Customer customer=restTemplate.getForObject(CostumerServiceUr1, Customer.class);

        Date currentDate=new Date(System.currentTimeMillis());

        String itemServiceUr1=itemServiceBaseUrl+ "/items/"+itemid;
        System.out.println(itemServiceUr1);
        Item item=restTemplate.getForObject(itemServiceUr1, Item.class);
        Purchase purchase=new Purchase(currentDate,customer.getId(),item.getId());
        purchaseRepo.save(purchase);
        return "The purchase was added to database";
    }

    @RequestMapping("/newbyget/{customerid}/{itemid}")
    public String newPurchaseByget(@PathVariable Long customerid,@PathVariable Long itemid ){
        String CostumerServiceUr1=customerServiceBaseUrl+"/customers/"+customerid;
        Customer customer=restTemplate.getForObject(CostumerServiceUr1, Customer.class);

        String itemServiceUr1=itemServiceBaseUrl+"/items/"+itemid;
        Item item=restTemplate.getForObject(itemServiceUr1, Item.class);


        Date currentDate=new Date(System.currentTimeMillis());

        Purchase purchase=new Purchase(currentDate,customer.getId(),item.getId());
        purchaseRepo.save(purchase);
        return "The purchase was added to database";
    }




}
//sökfram alla köp i databasen och filtrera efter vilken kund som gjort alla köpen
    /*List<Purchase> purchases=purchaseRepo.findAll();
      List<Purchase> customerPurchases=purchases.stream().filter(purchase ->
                purchase.getKund().equals(customer)).collect(Collectors.toList());
        return customerPurchases;*/

