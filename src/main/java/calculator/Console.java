package calculator;

import java.util.Scanner;

// 입출력 책임
public class Console {
    private static final String MESSAGE_INPUT_GUIDE = "수식을 입력해주세요.";
    private static final String MESSAGE_OUTPUT_FORMATTED = "결과 : %d";
    private static final String MESSAGE_ERROR_FORMAT = "[ERROR] 잘못된 수식입니다. 입력 형식을 확인해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    public String readInput() {
        System.out.println(MESSAGE_INPUT_GUIDE);
        return scanner.nextLine();
    }
    public void printResult(int result) {
        // TODO: 결과값 출력
    }
}
