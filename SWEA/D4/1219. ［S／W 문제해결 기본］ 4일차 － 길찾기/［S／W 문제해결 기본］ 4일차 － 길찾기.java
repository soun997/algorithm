import java.util.*;
import java.io.*;

public class Solution {

    static final int START = 0;
    static final int END = 99;

    static List<Integer>[] graph = new ArrayList[100];
    static boolean[] visited = new boolean[100];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= 10; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            graph = new ArrayList[100];
            visited = new boolean[100];
            for (int i = 0; i < 100; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }
            int result = (isPossible(START, false) ? 1 : 0);
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isPossible(int cur, boolean isPossible) {
        visited[cur] = true;
        if (cur == END) {
            return true;
        }
        for (int next : graph[cur]) {
            if (visited[next]) {
                continue;
            }
            isPossible = isPossible(next, isPossible);
        }
        return isPossible;
    }
}