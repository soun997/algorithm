import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Set<Integer> avoid = new HashSet<>();
    static Set<Integer> newAvoid = new HashSet<>();
    static Set<Integer>[] party;
    static Set<Integer>[] graph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new HashSet[N + 1];
        party = new HashSet[M];
        for (int i = 1; i <= N; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 0; i < M; i++) {
            party[i] = new HashSet<>();
        }
        st = new StringTokenizer(br.readLine());
        int avoidCount = Integer.parseInt(st.nextToken());
        if (avoidCount == 0) {
            System.out.println(M);
            return;
        }
        for (int i = 0; i < avoidCount; i++) {
            avoid.add(Integer.parseInt(st.nextToken()));
        }
        // 그래프 만들기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int joinCount = Integer.parseInt(st.nextToken());
            List<Integer> attendant = new ArrayList<>();
            for (int j = 0; j < joinCount; j++) {
                int p = Integer.parseInt(st.nextToken());
                attendant.add(p);
                party[i].add(p);
            }
            combination(0, 0, attendant, new int[2]);
        }
        // 기피대상 목록 갱신
        for (int a : avoid) {
            newAvoid.add(a);
            boolean[] visited = new boolean[N + 1];
            visited[a] = true;
            dfs(a, visited);
        }

        // 참가 가능한 파티
        int total = M;
        for (Set<Integer> list : party) {
            for (int a : newAvoid) {
                if (list.contains(a)) {
                    total--;
                    break;
                }
            }
        }
        System.out.println(total);
    }

    static void dfs(int cur, boolean[] visited) {

        for (int next : graph[cur]) {
            if (visited[next]) {
                continue;
            }
            newAvoid.add(next);
            visited[next] = true;
            dfs(next, visited);
        }
    }

    static void combination(int start, int cnt, List<Integer> attendant, int[] choices) {

        if (cnt == 2) {
            graph[choices[0]].add(choices[1]);
            graph[choices[1]].add(choices[0]);
            return;
        }

        for (int i = start; i < attendant.size(); i++) {
            choices[cnt] = attendant.get(i);
            combination(i + 1, cnt + 1, attendant, choices);
        }
    }
}