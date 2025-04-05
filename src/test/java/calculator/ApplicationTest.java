package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


public class ApplicationTest {

    @Test
    public void 빈_문자열_입력_시_0을_반환한다() {
        assertThat(Application.run("   ")).isEqualTo(0);
    }

    @Test
    public void 덧셈과_뺄셈만_포함된_수식_계산() {
        assertThat(Application.run("1+2-3")).isEqualTo(0);
    }

    @Test
    public void 곱셈과_덧셈_포함_수식_계산() {
        assertThat(Application.run("2*3+4")).isEqualTo(10);

    }

    @Test
    public void 잘못된_수식_입력_시_예외_발생() {
        assertThat(Application.run("1++2")).isEqualTo(-1);
    }

    // TODO: 테스트 코드 추가 가능합니다.
}
