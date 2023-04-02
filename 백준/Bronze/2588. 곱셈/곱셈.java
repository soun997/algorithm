import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String number1 = br.readLine();
        String number2 = br.readLine();
        char[] number2Arr = number2.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = number2Arr.length - 1; i >= 0; i--){
            sb.append(Integer.parseInt(number1) * Character.getNumericValue(number2Arr[i])).append("\n");
        }
        sb.append(Integer.parseInt(number1) * Integer.parseInt(number2));
        System.out.println(sb);
    }
}
