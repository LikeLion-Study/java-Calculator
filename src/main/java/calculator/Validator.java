package calculator;

import java.util.ArrayList;
import java.util.Set;

// 검사 책임

public class Validator {

    // 검사를 위한 배열 생성
    private final ArrayList<String> inputArrayList = new ArrayList<>();
    // 검사를 위한 연산자 set
    private static final Set<Character> OPERATORS = Set.of('+', '-', '*', '/');

    // 검사 이후 숫자와 연산자로 쪼개어 배열로 return
    public ArrayList<String> divideInput(String input) {
        String currentNumber = "";

        validateEmptyOrBlank(input);

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isDigit(ch)) {
                currentNumber += ch;

                validateLastNumber(i, input.length() - 1, currentNumber);

            } else if (OPERATORS.contains(ch)) {
                validateOperatorPosition(currentNumber);

                inputArrayList.add(currentNumber);
                inputArrayList.add(String.valueOf(ch));

                currentNumber = "";

            } else {  // 숫자나 문자열이 아닌 경우
                throw new IllegalArgumentException(Console.MESSAGE_ERROR_FORMAT);
            }
        }

        return inputArrayList;
    }

    private void validateLastNumber(int position, int length, String currentNumber) {
        if (position == length) {
            inputArrayList.add(currentNumber);
        }
    }

    private void validateEmptyOrBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalStateException(Console.MESSAGE_ERROR_EMPTY_OR_BLANK);
        }
    }

    private void validateOperatorPosition(String currentNumber) {
        if (currentNumber.isEmpty()) {
            throw new IllegalArgumentException(Console.MESSAGE_ERROR_WRONG_POSITION_OPERATORS);
        }
    }

}
