import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int[] dy = {-1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 10; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] ladder = new int[100][100];

            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                    //if ( ladder[i][j] == 2) System.out.println(i + " " + j);
                }
            }

            List<Integer> sp = new ArrayList<>();
            for (int i = 0; i < 100; i++){
                if (ladder[0][i] == 1) sp.add(i);
            }

            for (int i = 0; i < sp.size(); i++) {
                if (ladderGame(i, sp, ladder)){
                    sb.append("#").append(n).append(" ").append(sp.get(i)).append("\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    static boolean ladderGame(int idx, List<Integer> sp, int[][] ladder){

        for (int i = 1; i < 99; i++){

            for (int j = 0; j < 2; j++){
                int dir = sp.get(idx) + dy[j];
                if (dir < 0 || dir >= 100) continue;
                if (ladder[i][dir] == 1){
                    idx = idx + dy[j];
                    break;
                }
            }
        }

        if (ladder[99][sp.get(idx)] == 2) return true;
        return false;
    }
}
