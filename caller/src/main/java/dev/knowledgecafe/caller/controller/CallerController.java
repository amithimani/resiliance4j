package dev.knowledgecafe.caller.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/caller")
public class CallerController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL
            = "http://localhost:8081/";

    private static final String CALLER_SERVICE = "callerService";

    int count=1;

    @GetMapping
    @CircuitBreaker(name = CALLER_SERVICE, fallbackMethod = "serviceFallback")
    //@Retry(name = SERVICE_A)
   // @RateLimiter(name = CALLER_SERVICE)
    public String service() {

        String url = BASE_URL + "supplier";
        //System.out.println("Retry method called " + count++ + " times at " + new Date());
        return restTemplate.getForObject(
                url,
                String.class
        );
    }

    public String serviceFallback(Exception e) {
        return "Response returned from fallback method of caller service";
    }
}
