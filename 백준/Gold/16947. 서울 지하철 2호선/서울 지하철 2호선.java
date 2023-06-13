import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] parents;
    static boolean[] visited;
    static boolean[] isCycle;
    static boolean cycleChecked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        parents = new int[N + 1];
        visited = new boolean[N + 1];
        isCycle = new boolean[N + 1];
        cycleChecked = false;

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

        for (int i = 1; i <= N; i++) {
            if (isCycle[i]){
                result.append(0).append(" ");
                continue;
            }
            result.append(getDistance(i)).append(" ");
        }

        System.out.println(result);
    }

    static void findCircularLine(int prev, int cur){
        if (cycleChecked){
            return;
        }

        if (visited[cur]){
            if (!isCycle[cur]){
                parents[cur] = prev;
                findCycle(cur);
                cycleChecked = true;
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
            if (cycleChecked){
                return;
            }
        }
    }

    static void findCycle(int cur){

        if (isCycle[cur]){
            return;
        }

        isCycle[cur] = true;
        findCycle(parents[cur]);
    }

    static int getDistance(int start){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start, 0});
        visited = new boolean[N + 1];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (isCycle[cur[0]]){
                return cur[1];
            }
            for (int next : graph[cur[0]]) {
                if (visited[next]){
                    continue;
                }
                visited[next] = true;
                q.offer(new int[]{next, cur[1] + 1});
            }
        }
        return -1;
    }
}