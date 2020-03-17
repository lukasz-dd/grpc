package org.dixa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Should pass the method parameters provided by numbersProvider() method")
public class CalculatePrimesTest {

    private CalculatePrimes calculatePrimes;

    @BeforeEach
    public void setUp() {
        calculatePrimes = new CalculatePrimes();
    }

    @DisplayName(("Should calculate the correct prime numbers."))
    @ParameterizedTest(name = "{index} => number={0}")
    @MethodSource("numbersProvider")
    void shouldReturnCorrectPrimeNumbers(int input, List<Integer> expected) {
        List<Integer> calculated = calculatePrimes.calculate(input);
        assertThat(calculated).isEqualTo(expected);
    }

    private static Stream<Arguments> numbersProvider() {
        return Stream.of(
            Arguments.of(0, List.of()),
            Arguments.of(1, List.of()),
            Arguments.of(2, List.of(2)),
            Arguments.of(3, List.of(2,3)),
            Arguments.of(17, List.of(2, 3, 5, 7, 11, 13, 17))
        );
    }
}