import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Taxi taxi;
    static Map<Integer, Passenger> passengers;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static final int INF = Integer.MAX_VALUE / 1000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        taxi = new Taxi();
        passengers = new HashMap<>();

        taxi.fuel = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi.x = Integer.parseInt(st.nextToken());
        taxi.y = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            map[startX][startY] = i * 10;   // 승객의 ID
            passengers.put(i * 10, new Passenger(startX, startY, endX, endY));
        }

        for (int i = 0; i < M; i++) {

            int startFuel = driveToPassenger(taxi.x, taxi.y);
            // 가장 가까운 손님을 모시러 갈 수 있을 정도의 연료가 있다면
            if (startFuel > taxi.fuel){
                System.out.println(-1);
                return;
            }
            Passenger passenger = taxi.passenger;
            taxi.x = passenger.startX;
            taxi.y = passenger.startY;
            taxi.fuel = taxi.fuel - startFuel;

            // 손님을 목적지로 데려다 줄 수 있을 정도의 연료가 있다면
            int endFuel = driveToDestination(taxi.x, taxi.y, passenger.endX, passenger.endY);
            if (endFuel > taxi.fuel){
                System.out.println(-1);
                return;
            }

            // 소모한 연료의 2배 충전
            taxi.x = passenger.endX;
            taxi.y = passenger.endY;
            taxi.fuel = taxi.fuel + endFuel;
        }

        System.out.println(taxi.fuel);

    }

    // 사용한 연료양 반환
    static int driveToPassenger(int x, int y) {
        visited = new boolean[N + 1][N + 1];
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]){
                if (o1[0] == o2[0]){
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[2], o2[2]);
        });
        q.offer(new int[]{x, y, 0});  // 시작 X, 시작 Y
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            // 가장 가까이 있는 승객 발견!
            if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] != 1) {
                taxi.passenger = passengers.get(map[cur[0]][cur[1]]);
                map[cur[0]][cur[1]] = 0;
                return cur[2];  // 소모한 연료양
            }
            // 연료가 다 떨어졌으면 -> 영업 종료
            if (cur[2] > taxi.fuel) {
                return INF;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                // 이동 불가한 경우
                if (!check(nx, ny) || map[nx][ny] == 1 || visited[nx][ny]) {
                    continue;
                }
                q.offer(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny] = true;
            }
        }
        return INF;
    }

    static int driveToDestination(int startX, int startY, int endX, int endY){
        visited = new boolean[N + 1][N + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startX, startY, 0});  // 시작 X, 시작 Y
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == endX && cur[1] == endY) {
                return cur[2];  // 소모한 연료양
            }
            // 연료가 다 떨어졌으면 -> 영업 종료
            if (cur[2] > taxi.fuel) {
                return INF;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                // 이동 불가한 경우
                if (!check(nx, ny) || map[nx][ny] == 1 || visited[nx][ny]) {
                    continue;
                }
                q.offer(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny] = true;
            }
        }
        return INF;
    }

    static boolean check(int x, int y) {
        if (x < 1 || x >= N + 1 || y < 1 | y >= N + 1) {
            return false;
        }
        return true;
    }

    static class Taxi {

        int x;
        int y;
        int fuel;
        Passenger passenger;

    }

    static class Passenger {

        int startX;
        int startY;
        int endX;
        int endY;

        public Passenger(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
}