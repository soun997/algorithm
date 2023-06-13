import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] parents;
    static boolean[] visited;
    static boolean[] isCycle;
    static Queue<int[]> q;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        parents = new int[N + 1];
        visited = new boolean[N + 1];
        isCycle = new boolean[N + 1];
        q = new ArrayDeque<>();
        distance = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        findCircularLine(0, 1);
        getDistance();

        for (int i = 1; i <= N; i++) {
            result.append(distance[i]).append(" ");
        }

        System.out.println(result);
    }

    static void findCircularLine(int prev, int cur){

        if (visited[cur]){
            if (!isCycle[cur]){
                parents[cur] = prev;
                findCycle(cur);
            }
            return;
        }

        visited[cur] = true;
        parents[cur] = prev;

        for (int next : graph[cur]) {
            // 이전에 방문한 노드 중복 방문하지 않도록
            if (next == prev){
                continue;
            }
            findCircularLine(cur, next);
        }
    }

    static void findCycle(int cur){

        if (isCycle[cur]){
            q.offer(new int[]{cur, 0});
            return;
        }

        isCycle[cur] = true;
        findCycle(parents[cur]);
    }

    static void getDistance(){
        boolean[] visited = new boolean[N + 1];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int next : graph[cur[0]]) {
                if (visited[next]){
                    continue;
                }
                visited[next] = true;
                if (isCycle[next]){
                    q.offer(new int[]{next, 0});
                } else {
                    distance[next] = cur[1] + 1;
                    q.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
    }
}