package com.techreturners;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingScoreCalculatorTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/Records.csv", numLinesToSkip = 1)

    public void checkScoreforCsvInput (int expected, String input) {
        BowlingScoreCalculator bowlingScoreCalculator = new BowlingScoreCalculator();

        assertEquals(expected, bowlingScoreCalculator.calculate(input));
    }

}
