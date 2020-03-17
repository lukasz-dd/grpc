package org.dixa.controller;

import java.util.List;

import org.dixa.client.PrimeNumbersClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PrimeNumberController {

    private PrimeNumbersClient primeNumbersClient;

    @GetMapping("/primeList/{number}")
    public ResponseEntity<List<Integer>> getPrimeNumbers(@PathVariable("number") int number) {
        List<Integer> integers = primeNumbersClient.calculateList(number);

        return ResponseEntity.ok(integers);
    }
}
