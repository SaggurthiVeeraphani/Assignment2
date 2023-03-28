package com.example.stockAPI.Repository;

import com.example.stockAPI.Entity.StockSubscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockSubscriptionRepository extends MongoRepository<StockSubscription,Integer> {
}
