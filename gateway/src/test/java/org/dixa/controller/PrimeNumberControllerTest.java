package org.dixa.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dixa.client.PrimeNumbersClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrimeNumberControllerTest {

    private PrimeNumberController primeNumberController;
    private PrimeNumbersClient primeNumbersClient;

    @BeforeEach
    void setUp() {
        primeNumbersClient = mock(PrimeNumbersClient.class);
        primeNumberController = new PrimeNumberController(primeNumbersClient);
    }

    @Test
    void WhenGetPrimeNumbersCalled_Expect_PrimeNumbersClient_Called() {
        when(primeNumbersClient.calculateList(17)).thenReturn(List.of(1,2,3));
        primeNumberController.getPrimeNumbers(17);

        verify(primeNumbersClient).calculateList(17);
    }

}