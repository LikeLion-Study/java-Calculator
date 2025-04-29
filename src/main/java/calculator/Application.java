package calculator;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Calculator calculator = new Calculator();

        while (true) {
            String string = scanner.nextLine();
            System.out.println("수식을 입력해 주세요");

            if(string.contains("e")) break;
            System.out.println(calculator.calculate(string));
        }
    }
}
