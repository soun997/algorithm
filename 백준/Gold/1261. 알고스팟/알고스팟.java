import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static final int INFINITE = Integer.MAX_VALUE;
    static int N, M;
    static int[][] map;
    static PriorityQueue<Node> pq;
    static int[][] distance;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        dijkstra();

        if (distance[N - 1][M - 1] == INFINITE){
            System.out.println(0);
            return;
        }
        System.out.println(distance[N - 1][M - 1]);
    }


    static void dijkstra(){

        distance = new int[N][M];
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], INFINITE);
        }

        pq.offer(new Node(0, 0, 0));
        distance[0][0] = 0;

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();

            // 중복 탐색 방지1
            if (distance[minNode.x][minNode.y] < minNode.cost){
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = minNode.x + dx[d];
                int ny = minNode.y + dy[d];
                if (!check(nx, ny)){
                    continue;
                }
                // 중복 탐색 방지2
                if (distance[nx][ny] > minNode.cost + map[nx][ny]){
                    distance[nx][ny] = minNode.cost + map[nx][ny];
                    pq.offer(new Node(nx, ny, distance[nx][ny]));
                }
            }
        }
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= M){
            return false;
        }
        return true;
    }
}