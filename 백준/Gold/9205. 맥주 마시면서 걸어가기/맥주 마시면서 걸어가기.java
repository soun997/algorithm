import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] dist;
    static int[][] nodes;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            dist = new int[N + 2][N + 2];
            nodes = new int[N + 2][2];

            for (int i = 0; i < N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 2; j++) {
                    nodes[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++){
                    if (i == j) continue;
                    dist[i][j] = getManhattanDistance(nodes[i], nodes[j]);
                }
            }


            for (int k = 0; k < N + 2; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        if (dist[i][j] <= 1000){
                            dist[i][j] = 0;
                        }
                    }
                }
            }

            if (dist[0][N + 1] != 0){
                result = "sad";
            }
            else {
                result = "happy";
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static int getManhattanDistance(int[] start, int[] end){
        return Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
    }
}