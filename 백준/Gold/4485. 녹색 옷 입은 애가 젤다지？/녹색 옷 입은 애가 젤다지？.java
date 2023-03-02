import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int idx;
        int cost;

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static final int INFINITE = Integer.MAX_VALUE;
    static int testNumber;
    static int N;
    static int[][] map;
    static ArrayList<Node>[] graph;
    static int[] distance;
    static PriorityQueue<Node> pq;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        do {
            N = Integer.parseInt(br.readLine());
            if (N == 0){
                break;
            }
            map = new int[N][N];
            graph = new ArrayList[N * N];
            for (int i = 0; i < N * N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            makeGraph();

            dijkstra(0);

            sb.append("Problem ").append(testNumber + 1).append(": ").append(map[0][0] + distance[N * N - 1]).append("\n");
            testNumber++;
        } while(true);
        System.out.println(sb);
    }

    static void makeGraph(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if (!check(ni, nj)){
                        continue;
                    }
                    graph[N * i + j].add(new Node(N * ni + nj, map[ni][nj]));
                }
            }
        }
    }

    static void dijkstra(int start){

        distance = new int[N * N];
        pq = new PriorityQueue<>();
        Arrays.fill(distance, INFINITE);

        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();

            // 중복 탐색 방지1
            if (distance[minNode.idx] < minNode.cost){
                continue;
            }

            for (Node node : graph[minNode.idx]){

                // 중복 탐색 방지2
                if (distance[node.idx] > minNode.cost + node.cost){
                    distance[node.idx] = minNode.cost + node.cost;
                    pq.offer(new Node(node.idx, distance[node.idx]));
                }
            }
        }
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N){
            return false;
        }
        return true;
    }
}