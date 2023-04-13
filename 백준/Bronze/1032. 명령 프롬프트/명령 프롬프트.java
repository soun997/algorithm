import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] fileNames = new String[N];
        for (int i = 0; i < N; i++) {
            fileNames[i] = br.readLine();
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fileNames[0].length(); i++) {
            boolean isDifferent = false;
            char c = ' ';
            for (int k = 0; k < N; k++) {
                if (c == ' '){
                    c = fileNames[k].charAt(i);
                    continue;
                }
                if (c != fileNames[k].charAt(i)) {
                    isDifferent = true;
                    break;
                }
            }
            if (isDifferent){
                result.append('?');
                continue;
            }
            result.append(c);
        }
        System.out.println(result);
    }
}