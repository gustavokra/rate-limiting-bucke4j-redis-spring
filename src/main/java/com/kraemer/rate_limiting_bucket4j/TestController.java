package com.kraemer.rate_limiting_bucket4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController 
@RequestMapping("api/test")
public class TestController {
    
    @GetMapping("limited")
    public ResponseEntity<String> testLimit(@RequestHeader(value = "X-Test-Id", defaultValue = "default-user") String testId) {
        return ResponseEntity.ok("Request sucessful for test ID: " + testId);
    }
    
}
