import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static List<Integer>[] graph;
    static int min = Integer.MAX_VALUE;
    static List<Integer> possible = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) {
                break;
            }
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= V; i++) {
            int score = bfs(i);
            if (score < min) {
                possible.clear();
                possible.add(i);
                min = score;
                continue;
            }
            if (score == min) {
                possible.add(i);
            }
        }

        System.out.println(min + " " + possible.size());
        for (int idx : possible) {
            System.out.print(idx + " ");
        }
    }

    static int bfs(int start) {
        int max = -1;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[V + 1];
        q.offer(new int[]{ start, 0 });
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            max = Math.max(max, cur[1]);
            for (int next : graph[cur[0]]) {
                if (visited[next]) {
                    continue;
                }
                q.offer(new int[]{next, cur[1] + 1});
                visited[next] = true;
            }
        }
        return max;
    }
}