import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] solutions = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions);

        int p = 0;
        int q = solutions.length - 1;
        int minA = 0;
        int minB = 0;
        int minDiff = Integer.MAX_VALUE;

        while (p < q){

            int diff = Math.abs(solutions[p] + solutions[q]);
            if (diff < minDiff){
                minA = p;
                minB = q;
                minDiff = diff;
            }

            if (solutions[p] + solutions[q] < 0){
                p++;
                continue;
            }
            if (solutions[p] + solutions[q] > 0){
                q--;
                continue;
            }
            if (solutions[p] + solutions[q] == 0){
                minA = p;
                minB = q;
                break;
            }
        }

        System.out.println(solutions[minA] + " " + solutions[minB]);
    }
}