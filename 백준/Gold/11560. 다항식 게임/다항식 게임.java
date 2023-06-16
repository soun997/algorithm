import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 20;
    static int K, N;
    static long[][] coefficient;   // coefficient[a][b]: p(a)까지의 x^b 항의 계수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        calculatePolynomial();


        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            answer.append(coefficient[K][N]).append("\n");
        }
        System.out.println(answer);
    }

    static void calculatePolynomial(){

        coefficient = new long[MAX + 1][(MAX * (MAX + 1) / 2) + 1];
        coefficient[1][0] = 1;
        coefficient[1][1] = 1;

        for (int k = 2; k <= MAX; k++) {
            for (int i = 0; i < coefficient[k - 1].length; i++) {
                // 다항식 곱셈 계산
                if (coefficient[k - 1][i] != 0){
                    for (int j = 0; j <= k; j++) {
                        coefficient[k][j + i] = coefficient[k][j + i] + coefficient[k - 1][i];
                    }
                }
            }
        }
    }
}