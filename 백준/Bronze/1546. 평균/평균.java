import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        double[] subjects = new double[n];
        double m = 0;
        for (int i = 0; i < n; i++){
            subjects[i] = Double.parseDouble(st.nextToken());
            m = Math.max(m, subjects[i]);
        }

        double sum = 0;
        for (double s : subjects){
            sum += s / m * 100D;
        }

        System.out.println(sum / n);
    }
}
