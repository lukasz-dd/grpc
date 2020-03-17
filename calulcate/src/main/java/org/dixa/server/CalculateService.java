package org.dixa.server;

import java.util.List;

import org.dixa.service.CalculatePrimes;
import org.springframework.beans.factory.annotation.Autowired;

import com.dixa.prime.PrimeNumberIntResponse;
import com.dixa.prime.PrimeNumberRequest;
import com.dixa.prime.PrimesServiceGrpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class CalculateService extends PrimesServiceGrpc.PrimesServiceImplBase {

    private CalculatePrimes calculatePrimes;

    @Autowired
    public void setCalculatePrimes(CalculatePrimes calculatePrimes) {
        this.calculatePrimes = calculatePrimes;
    }

    @Override
    public void calculatePrimes(PrimeNumberRequest request, StreamObserver<PrimeNumberIntResponse> responseObserver) {
        int requestedNumber = request.getValue();
        log.info("Received calculate primes request for a number: {}", requestedNumber);

        List<Integer> calculatedPrimes = calculatePrimes.calculate(request.getValue());

        calculatedPrimes.stream()
            .map(value -> PrimeNumberIntResponse.newBuilder()
                .setCalculatedValues(value)
                .build())
            .forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }
}
