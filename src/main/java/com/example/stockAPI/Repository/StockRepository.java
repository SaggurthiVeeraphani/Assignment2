package com.example.stockAPI.Repository;

import com.example.stockAPI.Entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock,Integer> {
}
