package com.example.ems;

import com.example.ems.utils.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        Calculator calc = new Calculator();

        int sum = calc.add(5, 2);

        assertEquals(7, sum);
    }
}
