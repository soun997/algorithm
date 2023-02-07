import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        slice(0, 0, n);
        System.out.println(white);
        System.out.println(blue);
    }

    static void slice(int x, int y, int n){

        if (n == 1){
            if (map[x][y] == 0)
                white++;
            else
                blue++;
            return;
        }

        int past = map[x][y];
        for (int i = x; i < x + n; i++){
            for (int j = y; j < y + n; j++){
                if (past != map[i][j]){
                    slice(x, y, n / 2);
                    slice(x, y + n / 2, n / 2);
                    slice(x + n / 2, y, n / 2);
                    slice(x + n / 2, y + n / 2, n / 2);
                    return;
                }
            }
        }
        if (past == 0){
            white++;
        }
        else {
            blue++;
        }
        return;
    }
}
