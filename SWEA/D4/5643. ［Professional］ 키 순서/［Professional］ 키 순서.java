import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
    static int[][] adjMatrix;
    static boolean[] visited;
    static int[] myPrev;
    static int[] myNext;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            adjMatrix = new int[N + 1][N + 1];
            visited = new boolean[N + 1];
            myPrev = new int[N + 1];
            myNext = new int[N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjMatrix[a][b] = 1;    // a -> b
                adjMatrix[b][a] = 2;    // b -> a
            }

            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                visited[i] = true;
                findPrev(i, i);
                visited = new boolean[N + 1];
                visited[i] = true;
                findNext(i, i);
            }

            int total = 0;
            for (int i = 1; i <= N; i++){
                if (myPrev[i] + myNext[i] + 1 == N){
                    total++;
                }
            }

            sb.append("#").append(t).append(" ").append(total).append("\n");
        }
        System.out.println(sb);
    }

    static void findPrev(int prev, int start){

        for (int i = 1; i <= N; i++) {
            if (visited[i] || adjMatrix[prev][i] != 2){
                continue;
            }

            visited[i] = true;
            myPrev[start]++;
            findPrev(i, start);
        }
    }

    static void findNext(int next, int start){

        for (int i = 1; i <= N; i++) {
            if (visited[i] || adjMatrix[next][i] != 1){
                continue;
            }

            visited[i] = true;
            myNext[start]++;
            findNext(i, start);
        }
    }
}