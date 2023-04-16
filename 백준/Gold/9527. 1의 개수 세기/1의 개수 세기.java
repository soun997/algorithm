import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        if (A == B) {
            String binaryNumber = Long.toString(B, 2);
            int sum = 0;
            for (int i = 0; i < binaryNumber.length(); i++) {
                if (binaryNumber.charAt(i) == '1'){
                    sum++;
                }
            }
            System.out.println(sum);
            return;
        }

        // (A - 1)까지의 1의 합
        long resultA = calculateFrequencyOfOne(A - 1);
        //System.out.println("resultA = " + resultA);

        // B까지의 1의 합
        long resultB = calculateFrequencyOfOne(B);
        //System.out.println("resultB = " + resultB);

        // (B까지의 1의 합) - ((A - 1)까지의 1의 합)
        System.out.println(resultB - resultA);
    }

    static long calculateFrequencyOfOne(long number){
        if (number == 0){
            return 0;
        }
        
        if (number == 1){
            return 1;
        }
        String binaryNumber = Long.toString(number, 2);

        long boundNumber = (1L << (binaryNumber.length() - 1)) - 1;
        long boundNumberLength = Long.toString(boundNumber, 2).length();

        long sum = number - boundNumber; // // ex) 9 -> (1001 - (1000 - 1)) = 9 - 7
        sum += boundNumberLength * (1L << boundNumberLength - 1);

        //System.out.println("sum = " + sum);
        for (int i = 1; i < binaryNumber.length(); i++) {
           if (binaryNumber.charAt(i) == '1') {
               String innerBinaryNumber = binaryNumber.substring(i);
               sum += calculateFrequencyOfOne(Long.parseLong(innerBinaryNumber, 2));
               break;
           }
        }
        return sum;
    }
}