import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pair {

    public int x;
    public int y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int[][] board = new int[20][20];
    static int[] dx = {-1, 0, 1, 1, -1, -1, 0, 1};
    static int[] dy = {0, 1, -1, 1, -1, 1, -1, 0};
    static Pair[] omok = new Pair[5];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isDone = false;
        for (int i = 1; i < 20; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 20; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                if (board[i][j] == 0) continue;
                omok[0] = new Pair(i, j);
                if (isOmok(i, j)){
                    System.out.println(board[i][j]);
                    Pair max = omok[0];
                    for (int k = 1; k < 5; k++){
                        if (omok[k].y < max.y){
                            max = omok[k];
                        }
                    }
                    System.out.println(max.x + " " + max.y);
                    isDone = true;
                    break;
                }
            }
            if (isDone) break;
        }
        if (!isDone)
            System.out.println(0);
    }

    static boolean isOmok(int x, int y){
        for (int i = 0; i < 4; i++) {
            int cnt = 1;
            int weight = 1;
            while(true){
                int nx = x + dx[i] * weight;
                int ny = y + dy[i] * weight;
                if (nx < 1 || nx >= 20 || ny < 1 || ny >= 20) break;
                if (board[nx][ny] != board[x][y]) break;
                cnt++;
                if (cnt > 5) break;
                omok[cnt-1] = new Pair(nx, ny);
                weight++;
            }
            weight = 1;
            while(true){
                int nx = x + dx[7 - i] * weight;
                int ny = y + dy[7 - i] * weight;
                if (nx < 1 || nx >= 20 || ny < 1 || ny >= 20) break;
                if (board[nx][ny] != board[x][y]) break;
                cnt++;
                if (cnt > 5) break;
                omok[cnt-1] = new Pair(nx, ny);
                weight++;
            }
            if (cnt == 5) return true;
        }
        return false;
    }
}
