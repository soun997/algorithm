import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Fish implements Comparable<Fish> {
        public int x;
        public int y;
        public int distance = 0;

        public Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish other) {
            if (this.distance < other.distance) {
                return -1;
            } else if (this.distance == other.distance) {
                // 더 위에 있는 먹이 우선
                if (this.x < other.x) {
                    return -1;
                }
                // 더 왼쪽에 있는 먹이 우선
                else if (this.x == other.x) {
                    return Integer.compare(this.y, other.y);
                } else {
                    return 1;
                }
            } else {
                return 1;
            }

        }
    }
    static int N;
    static int[][] sea;
    static boolean[][] visited;
    static int time = 0;
    static int size = 2;
    static int eaten = 0;
    static int[] start;     // 상어의 시작위치
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];

        start = new int[2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                // 악어의 위치 초기화
                if (sea[i][j] == 9) {
                    start[0] = i;
                    start[1] = j;
                    sea[i][j] = 0;
                }
            }
        }
        while (true){
            if (!hunt()){
                break;
            }
        }
        System.out.println(time);
    }

    static boolean hunt() {

        visited = new boolean[N][N];
        visited[start[0]][start[1]] = true;

        PriorityQueue<Fish> q = new PriorityQueue<>();
        q.offer(new Fish(start[0], start[1], 0));
        while(!q.isEmpty()) {
            Fish cur = q.poll();
            // 현재 상어가 고기를 잡아먹을 수 있는 상태 (우선순위 큐에서 꺼냈으므로 가장 최적의 값)
            if (sea[cur.x][cur.y] < size && sea[cur.x][cur.y] != 0) {
                sea[cur.x][cur.y] = 0;
                time = time + cur.distance;
                eaten++;
                if (eaten == size){
                    size++;
                    eaten = 0;
                }
                // 다음 bfs 의 시작위치
                start[0] = cur.x;
                start[1] = cur.y;
                return true;
            }
            // 아니라면 다음 위치로 이동
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!check(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (sea[nx][ny] > size) {
                    continue;
                }
                q.offer(new Fish(nx, ny, cur.distance + 1));
                visited[nx][ny] = true;
            }

        }

        return false;
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N){
            return false;
        }
        return true;
    }
}