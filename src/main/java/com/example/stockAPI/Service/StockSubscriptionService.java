package com.example.stockAPI.Service;

import com.example.stockAPI.Entity.Stock;
import com.example.stockAPI.Entity.StockSubscription;
import com.example.stockAPI.Repository.StockSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StockSubscriptionService {
    @Autowired
    private StockSubscriptionRepository stockSubscriptionRepository;

    @Autowired
    private StockDataService stockDataService;

    // Method to save stock subscription to database
    /*public void save(Object object) {
        stockSubscriptionRepository.save(stockSubscription);
    }*/

    // Method to get stock details from database
    public Map<String, Map<String, Stock>> getStockDetails(String startDate, String endDate, String stockSymbol) {
        List<Stock> stocks = stockDataService.getStockData(startDate, endDate, stockSymbol);
        Map<String, Map<String, Stock>> stockDetails = new HashMap<>();
        for (Stock stock : stocks) {
            Map<String, Stock> stockData = new HashMap<>();
            stockData.put(stock.getDateTime(), stock);
            stockDetails.put(stock.getSymbol(), stockData);
        }
        return stockDetails;
    }
}
