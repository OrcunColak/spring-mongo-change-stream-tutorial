package com.colak.springtutorial.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String name;
    private double price;

}

