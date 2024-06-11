package com.baeldung.junit5.parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class MultipleSourcesUnitTest {

    @ParameterizedTest
    @CsvSource({ "MADRID,madrid", "ROME,rome" })
    @CsvSource({ "SPAIN,spain", "ITALY,italy" })
    void toLowerCase_ShouldGenerateTheExpectedLowercaseValue(String input, String expected) {
        String output = input.toLowerCase();
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("cities")
    @MethodSource("countries")
    void isEmpty_ShouldReturnFalseForArgsWithAtLestOneCharacter(String arg) {
        assertFalse(arg.isEmpty());
    }

    static Stream<String> cities() {
        return Stream.of("Madrid", "London", "Rome", "Paris");
    }

    static Stream<String> countries() {
        return Stream.of("Spain", "England", "Italy", "France");
    }

}
