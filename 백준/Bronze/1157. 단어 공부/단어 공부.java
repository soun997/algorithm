import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine().toUpperCase();
        int[] histogram = new int[26];
        for (int i = 0; i < word.length(); i++){
            histogram[word.charAt(i) - 65]++;
        }

        int max = 0;
        int idx = -1;
        for (int i = 0; i < histogram.length; i++) {
            if (histogram[i] > max) {
                max = histogram[i];
                idx = i;
            }
        }

        int cnt = 0;
        for (int i = 0; i < histogram.length; i++){
            if (histogram[i] == max)
                cnt++;
            if (cnt > 1 ){
                System.out.println("?");
                return;
            }
        }
        System.out.println((char)(idx + 65));
    }
}
