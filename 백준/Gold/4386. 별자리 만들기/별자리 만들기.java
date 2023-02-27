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
            this.weight = Math.sqrt(
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

    static int n;
    static int[] p;
    static int[] rank;
    static PriorityQueue<Edge> edges;
    static double[][] points;
    static int id;
    static int cnt;
    static double min;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        edges = new PriorityQueue<>();
        points = new double[n][2];
        id = 0;
        cnt = 0;
        min = 0D;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
        }

        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                edges.offer(new Edge(i, j));
            }
        }

        makeSet();
        while (cnt != n - 1){
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
        if (rank[x] < rank[y]){
            rank[y] += rank[x];
            p[x] = y;
        }
        else {
            rank[x] += rank[y];
            p[y] = x;
        }
        return true;
    }

    static int find(int x){
        if (x == p[x]) return x;
        return find(p[x]);
    }

    static void makeSet(){
        p = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            rank[i] = 1;
        }
    }
}