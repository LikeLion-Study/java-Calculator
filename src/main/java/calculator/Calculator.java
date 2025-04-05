package calculator;

import java.util.*;

// 연산 책임
public class Calculator {

    private int result = 0;

    public int calculate(ArrayList<String> inputArrayList) {

        // 단일 원소인 경우 그대로 return
        if (isSingleElement(inputArrayList)) {
            return Integer.parseInt(inputArrayList.get(0));
        }

        // 큐 선언
        LinkedList<String> stringQueue = toQueue(inputArrayList);

        while (stringQueue.size() > 1) {

            result = operate(
                    stringQueue.poll(),
                    stringQueue.poll(),
                    stringQueue.poll()
            );

            stringQueue.addFirst(String.valueOf(result));
        }

        return result;
    }


    private boolean isSingleElement(ArrayList<String> inputArrayList) {
        return inputArrayList.size() == 1;
    }

    private LinkedList<String> toQueue(ArrayList<String> inputArrayList) {
        return new LinkedList<>(inputArrayList);
    }

    private int operate(String num1String, String operator, String num2String) {
        int num1 = Integer.parseInt(num1String);
        int num2 = Integer.parseInt(num2String);

        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }
}
