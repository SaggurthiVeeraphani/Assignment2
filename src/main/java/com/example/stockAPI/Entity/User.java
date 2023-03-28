package com.example.stockAPI.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;


    private String lastName;

    @Column(unique = true)
    private String email;


    private String phoneNumber;


    private String password;

    private String sessionId;

    private List<StockSubscription> stockSubscriptions = new ArrayList<StockSubscription>();

}
