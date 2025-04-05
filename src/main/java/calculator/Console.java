package calculator;

import java.util.Scanner;

// TODO: SINGLETON?
// 입출력 책임
public class Console {
    private static final String MESSAGE_INPUT_GUIDE = "수식을 입력해주세요.";
    private static final String MESSAGE_OUTPUT_FORMATTED = "결과 : %d";

    public static final String MESSAGE_ERROR_FORMAT = "[ERROR] 잘못된 수식입니다. 입력 형식을 확인해 주세요.";
    public static final String MESSAGE_ERROR_WRONG_POSITION_OPERATORS = "[ERROR] 잘못된 연산자 위치입니다. 입력 형식을 확인해 주세요.";
    public static final String MESSAGE_ERROR_EMPTY_OR_BLANK = "[ERROR] 입력값이 없거나 공백입니다. 입력 형식을 확인해 주세요.";


    private static final Scanner scanner = new Scanner(System.in);

    public String readInput() {
        System.out.println(MESSAGE_INPUT_GUIDE);
        return scanner.nextLine();
    }

    public void printResult(int result) {
        System.out.println(String.format(MESSAGE_OUTPUT_FORMATTED, result));
    }
}
