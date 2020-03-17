package org.dixa.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dixa.prime.PrimeNumberIntResponse;
import com.dixa.prime.PrimesServiceGrpc.PrimesServiceBlockingStub;

class PrimeNumbersClientTest {

    private PrimesServiceBlockingStub primeService;
    private PrimeNumbersClient primeNumbersClient;

    @BeforeEach
    public void setUp() {
        primeNumbersClient = new PrimeNumbersClient();
        primeService = mock(PrimesServiceBlockingStub.class);
        primeNumbersClient.setPrimeService(primeService);
    }

    @Test
    public void WhenCalculateList_Expect_CorrectList() {
        List<PrimeNumberIntResponse> responseList = List.of(intResponse(2), intResponse(3), intResponse(5),
            intResponse(7), intResponse(11), intResponse(13), intResponse(17));
        Iterator<PrimeNumberIntResponse> iterator = responseList.iterator();
        when(primeService.calculatePrimes(any())).thenReturn(iterator);
        List<Integer> integers = primeNumbersClient.calculateList(17);
        assertThat(integers).isEqualTo(List.of(2, 3, 5, 7, 11, 13, 17));
    }

    private PrimeNumberIntResponse intResponse(int value) {
        return PrimeNumberIntResponse.newBuilder()
            .setCalculatedValues(value)
            .build();
    }
}