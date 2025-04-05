package calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void calculate_test_single() {
        ArrayList<String> input = new ArrayList<>(List.of("12"));
        int result = calculator.calculate(input);

        assertThat(result).isEqualTo(12);
    }

    @Test
    void calculate_test_simple() {
        ArrayList<String> input = new ArrayList<>(List.of("1", "+", "2"));
        int result = calculator.calculate(input);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void calculate_test_divide() {
        ArrayList<String> input = new ArrayList<>(List.of("4", "/", "2"));
        int result = calculator.calculate(input);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void calculate_test_complex() {
        ArrayList<String> input = new ArrayList<>(List.of("2", "+", "3", "*", "4"));
        int result = calculator.calculate(input);

        assertThat(result).isEqualTo(20);
    }

    @Test
    void calculate_test_wrong_operator() {
        ArrayList<String> input = new ArrayList<>(List.of("1", "&", "2"));

        assertThrows(IllegalStateException.class, () -> calculator.calculate(input));
    }
}
