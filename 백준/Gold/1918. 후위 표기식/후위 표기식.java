import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static char[] expression;
    static StringBuilder result;
    static Stack<Character> operators;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        expression = br.readLine().toCharArray();
        result = new StringBuilder();
        operators = new Stack<>();

        for (int i = 0; i < expression.length; i++) {

            // 알파벳일 때 -> 바로 출력 문자열에 add
            if (Character.isAlphabetic(expression[i])) {
                result.append(expression[i]);
                continue;
            }

            if (operators.isEmpty() || expression[i] == '('){
                operators.push(expression[i]);
                continue;
            }

            // 여는 괄호를 만날 때까지 출력
            if (expression[i] == ')') {
                addOperators();
                continue;
            }

            // 그 이외의 경우 -> 스택의 peek 와 우선순위 비교
            addOperators(expression[i]);
        }

        // 나머지 연산자들 출력
        addOperators();

        System.out.println(result);
    }

    static void addOperators() {
        while (!operators.isEmpty()) {

            if (operators.peek() == '('){
                operators.pop();
                break;
            }
            result.append(operators.pop());
        }
    }

    static void addOperators(char cur) {
        while (!operators.isEmpty()) {

            if (compareOperator(cur, operators.peek())){
                result.append(operators.pop());
                continue;
            }
            break;
        }
        operators.push(cur);
    }

    static boolean compareOperator(char cur, char prev) {

        if (prev == '+' || prev == '-') {
            // 현재 연산자의 우선순위가 더 작거나 같은 경우
            if (cur == '+' || cur == '-') {
               return true;
            }
        }

        if (prev == '*' || prev == '/') {
            // 현재 연산자의 우선순위가 더 작거나 같은 경우
            return true;
        }
        return false;
    }
}
