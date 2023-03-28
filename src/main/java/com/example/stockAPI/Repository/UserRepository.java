package com.example.stockAPI.Repository;

import com.example.stockAPI.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Integer> {

    User findByEmail(String email);
}
