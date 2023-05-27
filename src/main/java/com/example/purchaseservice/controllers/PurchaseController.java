package com.example.purchaseservice.controllers;

import com.example.purchaseservice.models.Purchase;
import com.example.purchaseservice.DTO.Customer;
import com.example.purchaseservice.DTO.Item;
import com.example.purchaseservice.repos.PurchaseRepo;
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


    @PostMapping("/newbypost/{customerid}/{itemid}")
    public String newPurchaseByPost(@PathVariable Long customerid,@PathVariable Long itemid ){
        String CostumerServiceUr1="http://localhost:8081/customers/"+customerid;
        Customer customer=restTemplate.getForObject(CostumerServiceUr1, Customer.class);

        Date currentDate=new Date(System.currentTimeMillis());

        String itemServiceUr1="http://localhost:8082/items/"+itemid;
        Item item=restTemplate.getForObject(itemServiceUr1, Item.class);
        //CUSTOMER ID INSTEAD , ITEM ID TOO
        Purchase purchase=new Purchase(currentDate,customer.getId(),item.getId());
        purchaseRepo.save(purchase);
        return "The purchase was added to database";
    }
    /*
    @RequestMapping("/newbyget/{customerid}/{itemid}")
    public String newPurchaseByget(@PathVariable Long customerid,@PathVariable Long itemid ){
        Customer customer=customerRepo.findById(customerid).get();
        Item item=itemRepo.findById(itemid).get();
        List<Item> itemsList=new ArrayList<>();
        itemsList.add(item);
        Date currentDate=new Date(System.currentTimeMillis());

        Purchase purchase=new Purchase(currentDate,customer,itemsList);
        purchaseRepo.save(purchase);
        return "The purchase was added to database";
    }*/
    /*
    @RequestMapping("/moreitems/{purchaseid}/{newitemsid}")
    public String addMoreItemsToPurchase(@PathVariable Long purchaseid,@PathVariable Long newitemsid){
        Purchase existingPurchase=purchaseRepo.findById(purchaseid).get();
        String itemServiceUr1="http://localhost:8082/items/"+newitemsid;
        Item newitem=restTemplate.getForObject(itemServiceUr1, Item.class);
        List<Item> itemList=existingPurchase.getItemsList();
        itemList.add(newitem);
        existingPurchase.setItemsList(itemList);
        if (existingPurchase !=null){
        purchaseRepo.save(existingPurchase);
        }
        return "The "+newitem.getName()+" was added to the existing purchase";
    }
    @RequestMapping("/forcustomer/{customerid}")
    public List<Purchase> customerPurchases(@PathVariable Long customerid){
        String customerur1="http://localhost:8080/customers/"+customerid;
        Customer customer=restTemplate.getForObject(customerur1, Customer.class);

        return purchaseRepo.findbyCustomer(customer);

    }*/




}
//sökfram alla köp i databasen och filtrera efter vilken kund som gjort alla köpen
    /*List<Purchase> purchases=purchaseRepo.findAll();
      List<Purchase> customerPurchases=purchases.stream().filter(purchase ->
                purchase.getKund().equals(customer)).collect(Collectors.toList());
        return customerPurchases;*/

