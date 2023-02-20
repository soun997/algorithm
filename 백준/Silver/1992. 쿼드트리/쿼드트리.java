import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[][] video;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        video = new int[n][n];
        sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                video[i][j] = Integer.parseInt(input[j]);
            }
        }

        find(0, 0, n);

        System.out.println(sb.toString());
    }

    static void find(int x, int y, int n){
        int stand = video[x][y];
        if (n == 1){
            sb.append(stand);
            return;
        }
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (video[i][j] != stand){
                    sb.append("(");
                    find(x, y, n / 2);
                    find(x, y + n / 2, n / 2);
                    find(x + n / 2, y, n / 2);
                    find(x + n / 2, y + n / 2, n / 2);
                    sb.append(")");
                    return;
                }
            }
        }
        sb.append(stand);
    }
}