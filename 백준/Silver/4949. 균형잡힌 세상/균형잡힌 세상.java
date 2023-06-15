import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        String str;
        while (!(str = br.readLine()).equals(".")){
            char[] chars = str.toCharArray();
            Stack<Character> stk = new Stack<>();
            for (char c : chars){
                if (Character.isAlphabetic(c) || c == ' ' || c == '.'){
                    continue;
                }
                if (!stk.isEmpty() && match(stk.peek(), c)){
                    stk.pop();
                    continue;
                }
                stk.push(c);
            }

            if (stk.isEmpty()){
                result.append("yes").append("\n");
                continue;
            }
            result.append("no").append("\n");
        }
        System.out.println(result);
    }

    static boolean match(char left, char right){
        if (left == '(' && right == ')') {
            return true;
        }
        if (left == '[' && right == ']') {
            return true;
        }
        return false;
    }
}