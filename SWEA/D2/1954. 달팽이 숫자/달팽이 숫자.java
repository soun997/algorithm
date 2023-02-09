import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    static int n;
    static int[][] arr;
    static int cnt;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < cases; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            cnt = 1;
            spin(0, 0, 1);

            sb.append("#").append(t + 1).append("\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void spin(int nx, int ny, int caseNum){
        if (cnt == n * n + 1)
            return;

        int loop = 0;
        switch(caseNum){
            case 1:
                for (int i = ny; i < n; i++){
                    loop = i;
                    if (arr[nx][i] != 0){
                        loop--;
                        break;
                    }
                    arr[nx][i] = cnt++;
                }
                spin(nx + 1, loop, 2);
                break;
            case 2:
                for (int i = nx; i < n; i++){
                    loop = i;
                    if (arr[i][ny] != 0){
                        loop--;
                        break;
                    }
                    arr[i][ny] = cnt++;
                }
                spin(loop, ny - 1, 3);
                break;
            case 3:
                for (int i = ny; i >= 0; i--){
                    loop = i;
                    if (arr[nx][i] != 0) {
                        loop++;
                        break;
                    }
                    arr[nx][i] = cnt++;
                }
                spin(nx - 1, loop, 4);
                break;
            case 4:
                for (int i = nx; i >= 0; i--){
                    loop = i;
                    if (arr[i][ny] != 0){
                        loop++;
                        break;
                    }
                    arr[i][ny] = cnt++;
                }
                spin(loop, ny + 1, 1);
                break;
        }
    }
}
