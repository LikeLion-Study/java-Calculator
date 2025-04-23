package calculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    Validator validator = new Validator();

    @Test
    void split_test() {
        String input = "3+5*2";
        List<String> result = validator.divideInput(input);

        assertThat(result).containsExactly("3", "+", "5", "*", "2");
    }

    @Test
    void exception_test_for_blank() {
        String input = "   ";
        Exception e = assertThrows(IllegalStateException.class, () -> validator.divideInput(input));

        assertThat(e.getMessage()).isEqualTo(Console.MESSAGE_ERROR_EMPTY_OR_BLANK);
    }

    @Test
    void exception_test_for_operators() {
        String input = "3++2";
        Exception e = assertThrows(IllegalArgumentException.class, () -> validator.divideInput(input));

        assertThat(e.getMessage()).isEqualTo(Console.MESSAGE_ERROR_WRONG_POSITION_OPERATORS);
    }

    @Test
    void exception_test_for_input_format() {
        String input = "5+a";
        Exception e = assertThrows(IllegalArgumentException.class, () -> validator.divideInput(input));

        assertThat(e.getMessage()).isEqualTo(Console.MESSAGE_ERROR_FORMAT);
    }

    @Test
    void exception_test_for_first_starts_with_operator() {
        String input = "+12+3-5";
        Exception e = assertThrows(IllegalArgumentException.class, () -> validator.divideInput(input));

        assertThat(e.getMessage()).isEqualTo(Console.MESSAGE_ERROR_WRONG_POSITION_OPERATORS);

    }

}
