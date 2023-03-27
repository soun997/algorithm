import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[9][9];
        int max = Integer.MIN_VALUE;
        int x = -1;
        int y = -1;

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > max){
                    x = i;
                    y = j;
                    max = arr[i][j];
                }
            }
        }
        System.out.println(max);
        System.out.println((x + 1) + " " + (y + 1));
    }
}
