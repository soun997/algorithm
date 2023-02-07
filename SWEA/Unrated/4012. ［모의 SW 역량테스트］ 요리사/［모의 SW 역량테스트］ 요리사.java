import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static int[] p;
    static int[] nums;
    static boolean[] visited;
    static int[][] synergy;
    static int min;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < cases; t++) {

            n = Integer.parseInt(br.readLine());
            p = new int[n];
            nums = new int[n / 2];
            visited = new boolean[n];
            synergy = new int[n][n];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                p[i] = i + 1;
            }

            for (int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            combi(0, 0);

            sb.append("#").append(t + 1).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }

    static void combi(int cnt, int start) {
        if(cnt == n / 2) {
            min = Math.min(min, calculate(visited));
            return;
        }
        for (int i = start; i < n; i++) {
            visited[i]=true;
            combi(cnt+1, i+1);
            visited[i]=false;
        }
    }

    static int calculate(boolean[] selected){

        int cntA = 0;
        int cntB = 0;
        int[] synergyA = new int[n / 2];
        int[] synergyB = new int[n / 2];
        for (int i = 0; i < n; i++) {
            if (selected[i]){
                synergyA[cntA++] = p[i];
            }
            else {
                synergyB[cntB++] = p[i];
            }
        }
        int resultA = 0;
        int resultB = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++){
                // if (i == j) continue;
                resultA += (synergy[synergyA[i] - 1][synergyA[j] - 1] + synergy[synergyA[j] - 1][synergyA[i] - 1]);
                resultB += (synergy[synergyB[i] - 1][synergyB[j] - 1] + synergy[synergyB[j] - 1][synergyB[i] - 1]);
            }
        }

        return Math.abs(resultA - resultB);
    }
}
