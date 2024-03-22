import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] p, rank;
    static int cnt;
    static long minCost;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge other) {

            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= V; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i == j) {
                    continue;
                }
                pq.offer(new Edge(i, j, cost));
            }
        }

        makeSet();

        while (cnt < V - 1) {
            Edge cur = pq.poll();
            if (union(cur.from, cur.to)) {
                cnt++;
                minCost += cur.cost;
            }
        }
        System.out.println(minCost);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        // 같은 그룹
        if (px == py) {
            return false;
        }
        if (rank[px] > rank[py]) {
            p[px] = p[py];
        }
        if (rank[px] == rank[py]) {
            p[px] = p[py];
            rank[py]++;
        }
        if (rank[px] < rank[py]) {
            p[py] = p[px];
        }
        return true;
    }

    static int find(int x) {
        if (x == p[x]) {
            return x;
        }
        return p[x] = find(p[x]);   // 좌표압축
    }

    static void makeSet() {
        p = new int[V + 1];
        rank = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            p[i] = i;
            rank[i] = 1;
        }
    }
}