package com.example.stockAPI.Controller;

import com.example.stockAPI.Entity.Stock;
import com.example.stockAPI.Entity.StockSubscription;
import com.example.stockAPI.Entity.User;
import com.example.stockAPI.Repository.StockSubscriptionRepository;
import com.example.stockAPI.Service.StockSubscriptionService;
import com.example.stockAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StockSubscriptionController {
    @Autowired
    UserService userService;

    @Autowired
    StockSubscriptionService stockSubscriptionService;

    @Autowired
    StockSubscriptionRepository stockSubscriptionRepository;


    // User registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("User registration successful");
    }

    // User login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userService.login(user.getEmail(), user.getPassword())) {
            String sessionId = userService.generateSessionId(user.getEmail());
            return ResponseEntity.ok(sessionId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Endpoint to subscribe to stock ticker
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToStockTicker(@RequestBody StockSubscription stockSubscription,@RequestHeader String token) {
        String symbol = stockSubscription.getStockSymbol();
        System.out.println(token);
        System.out.println(symbol);
        RestTemplate restTemplate = new RestTemplate();
        StockSubscription stockSubscription1 = new StockSubscription();

        stockSubscription1.setStockSymbol(symbol);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<StockSubscription> entity = new HttpEntity<StockSubscription>(stockSubscription1,headers);


        ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) restTemplate.exchange(
                "https://www.alphavantage.co/documentation/", HttpMethod.POST, entity, Object.class).getBody();
        stockSubscriptionRepository.save();

        return ResponseEntity.ok("Stock subscription successful");
    }

    // Endpoint to get stock details
    @GetMapping("/stock-details")
    public ResponseEntity<Map<String, Map<String, Stock>>> getStockDetails(@RequestParam String startDate,
                                                                           @RequestParam String endDate,
                                                                           @RequestParam String stockSymbol) {
        Map<String, Map<String, Stock>> stockDetails = stockSubscriptionService.getStockDetails(startDate, endDate, stockSymbol);
        return ResponseEntity.ok(stockDetails);
    }

}
