package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    Calculator calculator = new Calculator();

    @Test
    public void 빈_문자열_입력_시_0을_반환한다() {
        // 아직 calculate 함수가 구현되지 않은 상태에서 TDD Red Test용 코드
        int result = calculator.calculate("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void 덧셈과_뺄셈만_포함된_수식_계산() {
        int result = calculator.calculate("1+2-3");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void 곱셈과_덧셈_포함_수식_계산() {
        int result = calculator.calculate("2*3+4");
        assertThat(result).isEqualTo(10);
    }

    @Test
    public void 잘못된_수식_입력_시_예외_발생() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate("1++2");
        });
    }

    @Test
    public void 나눈_나머지를_포함한_수식_계산() {
        int result = calculator.calculate("5%2+10");
        assertThat(result).isEqualTo(11);
    }

    @Test
    public void 음수로_시작하는_수식_계산() {
        int result = calculator.calculate("-10+50*5");
        assertThat(result).isEqualTo(240);
    }

}
