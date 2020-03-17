package org.dixa.server;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dixa.service.CalculatePrimes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dixa.prime.PrimeNumberIntResponse;
import com.dixa.prime.PrimeNumberRequest;

import io.grpc.stub.StreamObserver;

class CalculateServiceTest {

    private CalculateService calculateService;
    private CalculatePrimes calculatePrimes;
    private PrimeNumberRequest primeNumberRequest;
    private StreamObserver<PrimeNumberIntResponse> responseObserver;

    @BeforeEach
    void setUp() {
        calculateService = new CalculateService();
        calculatePrimes = mock(CalculatePrimes.class);
        responseObserver = mock(StreamObserver.class);
        calculateService.setCalculatePrimes(calculatePrimes);
    }

    @Test
    void calculatePrimes() {
        primeNumberRequest = PrimeNumberRequest.newBuilder().setValue(17).build();
        when(calculatePrimes.calculate(primeNumberRequest.getValue())).thenReturn(List.of(1,6));

        calculateService.calculatePrimes(primeNumberRequest, responseObserver);

        verify(responseObserver).onNext(eq(PrimeNumberIntResponse.newBuilder()
            .setCalculatedValues(1)
            .build()));

        verify(responseObserver).onNext(eq(PrimeNumberIntResponse.newBuilder()
            .setCalculatedValues(6)
            .build()));

        verify(responseObserver).onCompleted();
    }
}