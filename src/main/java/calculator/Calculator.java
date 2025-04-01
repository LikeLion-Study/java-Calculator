package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    List<Integer> values;
    List<Character> operator;

    public Calculator() {
        values = new ArrayList<>();
        operator = new ArrayList<>();
    }

    /**
     * 1. 숫자와 연산자를 분리합니다.
     * 2. 계산할 수 있는 수식인지 검사합니다.
     * (숫자 개수 = n, 연산자 계수 = n-1)
     * 3. 우선순위에 맞게 계산을 수행합니다.
     * 4. 출력합니다.
     */
    public int calculate(String input) {
        // 0. 이전 계산 상태 초기화
        values.clear();
        operator.clear();

        // 1. 숫자와 연산자 분리
        stringParser(input);

        // 2. 유효성 검사
        if (values.isEmpty()) {
            if (operator.isEmpty()) return 0;
            else throw new IllegalArgumentException("수식에 숫자가 없습니다.");
        }

        if (!operator.isEmpty() && values.size() != operator.size() + 1) {
            throw new IllegalArgumentException("잘못된 수식 형태입니다. 숫자와 연산자의 개수가 맞지 않습니다.");
        }

        if (operator.isEmpty()) {
            return values.get(0);
        }

        // 3. 계산 실행 (연산자 우선순위대로 적용)
        for (int i = 1; i <= 15; i++) {
            calculatePass(i);
        }

        // 4. 최종 결과 반환
        if (values.size() == 1 && operator.isEmpty()) {
            return values.get(0);
        } else {
            // 계산 로직 오류 또는 잘못된 수식 처리 실패 시
            System.err.println("계산 후 남은 값: " + values);
            System.err.println("계산 후 남은 연산자: " + operator);
            throw new IllegalArgumentException("계산 로직 오류: 최종 결과 도출에 실패했습니다.");
        }
    }

    /**
     * 문자열을 숫자와 연산자를 분리하고 리스트에 저장합니다.
     * <p>
     * 1.문자가 숫자일 때, 버퍼에 저장합니다.
     * 2.숫자도, 연산자도 아닐 때 예외 처리합니다.
     * 3.숫자가 나오기 전에 먼저 연산자가 나온 경우
     * 3. 버퍼를 초기화하고 저장된 숫자를 리스트에 추가합니다.
     */
    private void stringParser(String input) {
        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber.append(c);
                continue;
            }

            if (!isOperator(c))
                throw new IllegalArgumentException("지원하지 않는 문자입니다: " + c);

            if (c == '-' && (values.isEmpty() || (i > 0 && isOperator(input.charAt(i - 1))))) {
                currentNumber.append(c);
                continue;
            } else if (values.isEmpty() && currentNumber.length() == 0) {
                throw new IllegalArgumentException("수식이 숫자로 시작해야 합니다 (단, 음수 부호 제외): " + input);
            }

            if (currentNumber.length() > 0) {
                values.add(Integer.parseInt(currentNumber.toString()));
                currentNumber.setLength(0);
            }

            operator.add(c);
        }

        if (currentNumber.length() > 0) {
            values.add(Integer.parseInt(currentNumber.toString()));
        }
    }

    /**
     * 지정된 우선순위에 해당하는 연산을 수행합니다.
     * 리스트를 뒤에서부터 순회하여 인덱스 문제를 방지합니다.
     */
    private void calculatePass(int priority) {
        for (int i = operator.size() - 1; i >= 0; i--) {
            char op = operator.get(i);
            if (getPriority(op) == priority) {
                // 해당 우선순위의 연산자를 찾으면
                int left = values.get(i);
                int right = values.get(i + 1);
                int result = operation(left, right, op);

                values.set(i, result);
                values.remove(i + 1);
                operator.remove(i);
            }
        }
    }

    /**
     * 두 숫자와 연산자 하나를 받아 실제 계산을 수행
     */
    private int operation(int a, int b, char op) {
        switch (op) {
            case '+':
                return add(a, b);
            case '-':
                return subtract(a, b);
            case '*':
                return multiply(a, b);
            case '/':
                return divide(a, b);
            case '%':
                return modulo(a, b);
            default:
                throw new IllegalArgumentException("알 수 없는 연산자: " + op);
        }
    }

    /**
     * 해당 문자가 기능에 포함된 연산자인지 확인하는 헬퍼 메소드
     */
    private boolean isOperator(char c) {
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
                return true;
            default:
                return false;
        }
    }

    /**
     * 연산자 우선 순위를 반환합니다. (값이 작을수록 우선순위가 높도록 재정의)
     * https://en.wikipedia.org/wiki/Order_of_operations#Programming_languages
     * 3: *, /, %
     * 4: +, -
     */
    public int getPriority(char c) {
        switch (c) {
            case '*':
            case '/':
            case '%':
                return 3;
            case '+':
            case '-':
                return 4;
            default:
                return 15;
        }
    }

    /**
     * 덧셈 계산
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 뺄셈 계산
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * 곱셈 계산
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * 나눗셈 계산
     */
    public int divide(int a, int b) {
        if (b == 0) {
            System.out.println("0으로 나눌 수 없습니다.");
            throw new IllegalArgumentException("0으로 나눌 수 없습니다."); // 예외 메시지 일관성
        }
        return a / b;
    }

    /**
     * 나눈 나머지 계산
     */
    public int modulo(int a, int b) {
        if (b == 0) {
            System.out.println("0으로 나눌 수 없습니다.");
            throw new IllegalArgumentException("0으로 나눌 수 없습니다."); // 예외 메시지 일관성
        }
        return a % b;
    }
}