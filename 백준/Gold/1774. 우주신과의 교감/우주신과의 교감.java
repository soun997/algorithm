import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {

        int start;
        int end;
        double weight;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
            this.weight = pow(start, end);
        }

        private double pow(int start, int end){
            return Math.sqrt(
                    Math.pow(Math.abs(points[start][0] - points[end][0]), 2.0)
                            + Math.pow(Math.abs(points[start][1] - points[end][1]), 2.0)
            );
        }


        @Override
        public int compareTo(Edge other) {
            if (this.weight < other.weight){
                return -1;
            }
            else if (this.weight == other.weight){
                return 0;
            }
            else {
                return 1;
            }
        }
    }

    static int N, M;
    static int[] p;
    static int[] rank;
    static PriorityQueue<Edge> edges;
    static int[][] points;
    static int cnt;
    static int dup;
    static double min;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>();
        points = new int[N][2];
        cnt = 0;
        dup = 0;
        min = 0D;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                edges.offer(new Edge(i, j));
            }
        }

        makeSet();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            if (!union(x, y)){
                dup++;
            }
        }

        while (cnt < N - 1 - M + dup){
            Edge edge = edges.poll();
            if (union(edge.start, edge.end)){
                cnt++;
                min += edge.weight;
            }
        }
        System.out.println(String.format("%.2f", min));
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if (x == y) return false; // 이미 MST 에 포함된 경우(Union 필요 X)
        if (rank[x] > rank[y]){
            p[y] = x;
        }
        else if (rank[x] == rank[y]){
            p[y] = x;
            rank[x]++;
        }
        else {
            p[x] = y;
        }
        return true;
    }

    static int find(int x){
        if (x == p[x]) return x;
        return p[x] = find(p[x]);
    }

    static void makeSet(){
        p = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 1;
        }
    }
}