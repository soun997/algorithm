import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static class Pair {

	    public int x;
	    public int y;

	    public Pair(int x, int y){
	        this.x = x;
	        this.y = y;
	    }
	}

    static int[][] board = new int[20][20];
    static int[] dx = {-1, 0, 1, 1, -1, -1, 0, 1};
    static int[] dy = {0, 1, -1, 1, -1, 1, -1, 0};
    static Pair[] omok = new Pair[5];
    static int cnt = 1;

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

    static boolean isOmok(int x, int y) {
    	
    	omok[0] = new Pair(x, y);
        for (int i = 0; i < 4; i++) {
            cnt = 1;        
            getStones(x, y, i);
            getStones(x, y, 7 - i);
            if (cnt == 5) return true;
        }
        return false;
    }
    
    static void getStones(int x, int y, int d) {
    	int weight = 1;
    	while(true){
            int nx = x + dx[d] * weight;
            int ny = y + dy[d] * weight;
            if (nx < 1 || nx >= 20 || ny < 1 || ny >= 20) break;
            if (board[nx][ny] != board[x][y]) break;
            cnt++;
            if (cnt > 5) break;
            omok[cnt-1] = new Pair(nx, ny);
            weight++;
        }
    }
}