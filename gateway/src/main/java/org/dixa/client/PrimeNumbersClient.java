package org.dixa.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dixa.prime.PrimeNumberRequest;
import com.dixa.prime.PrimesServiceGrpc.PrimesServiceBlockingStub;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
@Slf4j
public class PrimeNumbersClient {

    private PrimesServiceBlockingStub  primeService;

    @GrpcClient("localhost:50051")
    public void setPrimeService(PrimesServiceBlockingStub primeService) {
        this.primeService = primeService;
    }

    public List<Integer> calculateList(int number) {
        List<Integer> primeNumbers = new ArrayList<>();
        PrimeNumberRequest request = PrimeNumberRequest.newBuilder()
            .setValue(number)
            .build();

        log.info("Sending calculate primes request to the server...");

        primeService.calculatePrimes(request)
            .forEachRemaining(primeNumberIntResponse ->
                    primeNumbers.add(primeNumberIntResponse.getCalculatedValues())
                );

        log.info("Calculated. Prime numbers before {}: {}", number, primeNumbers);

        return primeNumbers;
    }

}
