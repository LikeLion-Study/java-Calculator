package calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("계산할 수식을 입력하세요: ");
        String input = sc.nextLine();

        System.out.println("입력된 수식: " + input);

        Calculator c = new Calculator();
        int result = c.calculate(input);
    }

    public int calculate(String input) {
        int num = 0;
        int number = 0;
        char operation = ' ';
        StringBuilder sb = new StringBuilder();

        input = input.trim().replaceAll("\\s+", "");
        boolean isValid = input.matches("[0-9]+([+\\-*/][0-9]+)*");

        if (!isValid) {
            throw new IllegalArgumentException("잘못된 수식 형식입니다.");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                if (sb.length() == 0) {
                    System.out.println("숫자 없이 연산자가 들어왔습니다!");
                    return 0;
                }

                number = Integer.parseInt(sb.toString());

                if (operation == ' ') {
                    num = number;
                } else if (operation == '+') {
                    num += number;
                } else if (operation == '-') {
                    num -= number;
                } else if (operation == '*') {
                    num *= number;
                } else if (operation == '/') {
                    num /= number;
                }

                sb.setLength(0);
                operation = ch;
            }
        }

        if (sb.length() > 0) {
            number = Integer.parseInt(sb.toString());

            if (operation == '+') num += number;
            else if (operation == '-') num -= number;
            else if (operation == '*') num *= number;
            else if (operation == '/') num /= number;
        }

        System.out.println("계산 결과: " + num);
        return num;
    }
}
