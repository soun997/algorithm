import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] sea;
    static boolean[][] check;
    static int[] dx = {-1, 0, 0, 1,};
    static int[] dy = {0, -1, 1, 0};
    static int sec = 0;
    static int size = 2;
    static int r;
    static int c;
    static int eaten = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sea = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 9) {
                    r = i;
                    c = j;
                    sea[i][j] = 0;
                }
            }
        }
        while(hunt()) {}
        System.out.println(sec);
    }

    static boolean hunt() {
        check = new boolean[n][n];
        check[r][c] = true;
        PriorityQueue<Pair> q = new PriorityQueue<Pair>();
        q.offer(new Pair(r, c, 0));
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            if (sea[cur.x][cur.y] < size && sea[cur.x][cur.y] != 0) {
                sea[cur.x][cur.y] = 0;
                sec = sec + cur.distance;
                eaten++;
                if (eaten == size){
                    size++;
                    eaten = 0;
                }
                r = cur.x;
                c = cur.y;
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (check[nx][ny]) continue;
                if (sea[nx][ny] > size) continue;
                q.offer(new Pair(nx, ny, cur.distance + 1));
                check[nx][ny] = true;
            }

        }

        return false;
    }
}

class Pair implements Comparable<Pair> {
    public int x;
    public int y;
    public int distance = 0;
    public Pair(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(Pair target) {
        if (this.distance < target.distance){
            return -1;
        }
        else if (this.distance == target.distance){
            // 더 위에 있는 먹이 우선
            if (this.x < target.x) {
                return -1;
            }
            // 더 왼쪽에 있는 먹이 우선
            else if (this.x == target.x) {
                if (this.y < target.y) {
                    return -1;
                }
            }
        }
        return 1;
    }
}

