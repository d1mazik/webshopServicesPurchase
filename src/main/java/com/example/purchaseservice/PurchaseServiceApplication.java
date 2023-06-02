package com.example.purchaseservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*--------------------------------------------------------------------
create database purchase_service_db ;
 ---------------------------------------------------------------------*/
@SpringBootApplication
public class PurchaseServiceApplication {

    public static void main(String[] args) {
        /*
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.configure().load();

        // Access environment variables
        String password = dotenv.get("WEBSHOPSERVICES_PW");

         */


        SpringApplication.run(PurchaseServiceApplication.class, args);
    }

}
