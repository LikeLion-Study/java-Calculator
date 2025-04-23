package calculator;

import java.util.ArrayList;

public class Application {

    private static final Console console = new Console();

    public static void main(String[] args) {
        String inputString = console.readInput();

        run(inputString);
    }

    public static int run(String inputString) {
        Validator validator = new Validator();
        Calculator calculator = new Calculator();

        ArrayList<String> inputArrayList;
        int result = 0;

        try {
            inputArrayList = validator.divideInput(inputString);
            result = calculator.calculate(inputArrayList);
        } catch (IllegalStateException e) {
            // 입력값이 공백값 또는 비어있는 경우
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return -1;
        }

        console.printResult(result);
        return result;
    }
}
