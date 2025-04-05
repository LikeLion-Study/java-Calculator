package calculator;
import java.util.Objects;

public class Calculator {
    public int calculate(String input) {
        // TODO: 코드 구현
        if(input.isEmpty()){
            return 0;
        }
        int result=0;
        int num=0;
        char b='+';
        for(int i=0;i<input.length();i++){
            char a=input.charAt(i);
            boolean isNum = Character.isDigit(a);

            if(isNum){
                num=a-'0';
                switch(b){
                    case '+': result+=num; break;
                    case '-': result-=num; break;
                    case '*': result*=num; break;
                    case '/':
                        if(num==0){
                            throw new IllegalArgumentException("0 입력 불가능");
                        }
                        result/=num; break;
                }
            }
            else if(a=='+' || a=='-' || a=='*' || a=='/'){
                b=a;
            }
                throw new IllegalArgumentException("잘못된 수식 입력");
        }

        return result;
    }
}
