import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Integer> marbles;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        marbles = new ArrayList<>();
        marbles.add(-1);
        result = new int[4];

        // 구슬 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init(N / 2, N / 2); // 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());
            blizzard(dir, distance);
        }

        System.out.println(result[1] + 2 * result[2] + 3 * result[3]);
    }

    // 달팽이 모양으로 맵을 채운다
    // 구슬 리스트를 만든다
    static void init(int x, int y) {
        // 좌, 하, 우, 상
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int idx = 1;
        int max = 1;
        int dir = 0;
        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int k = 0; k < max; k++) {
                    x = x + dx[dir];
                    y = y + dy[dir];
                    if (map[x][y] != 0) {
                        marbles.add(map[x][y]);
                    }
                    map[x][y] = idx++;
                    if (x == 0 && y == 0) {
                        return;
                    }
                }
                dir = (dir + 1) % 4;
            }
            max++;
        }
    }

    // 블리자드를 쏜다
    static void blizzard(int dir, int distance) {
        // 상, 하, 좌, 우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int x = N / 2;
        int y = N / 2;
        int idx = 0;
        while (distance > 0) {
            x = x + dx[dir];
            y = y + dy[dir];
            if (map[x][y] - idx > marbles.size() - 1){
                break;
            }
            marbles.remove(map[x][y] - idx++);  // primitive 타입이기 때문에 index 삭제로 수행
            distance--;
        }

        while (explosion()){}
        transform();
    }

    // 연속된 구슬을 폭발시킨다
    static boolean explosion() {

        boolean flag = false;
        int conscious = 1;
        for (int i = marbles.size() - 1; i > 0; i--) {

            // 연속된 구슬
            if (marbles.get(i) == marbles.get(i - 1)) {
                conscious++;
            } else {
                if (conscious >= 4) {
                    flag = true;
                    int num = marbles.get(i);
                    result[num] = result[num] + conscious;
                    for (int k = i + conscious - 1; k >= i; k--) {
                        marbles.remove(k);
                    }
                }
                conscious = 1;
            }
        }

        return flag;
    }

    static void transform() {
        List<Integer> newMarbles = new ArrayList<>();
        int marbleCount = marbles.size();
        int left = 1;
        int right = 1;
        while (right < marbleCount) {
            if (marbles.get(left).equals(marbles.get(right))) {
                right++;
                continue;
            }

            int num = marbles.get(left);
            newMarbles.add(right - left);
            newMarbles.add(num);
            left = right;
        }

        if (left < marbleCount) {
            int num = marbles.get(left);
            newMarbles.add(right - left);
            newMarbles.add(num);
        }

        marbles = new ArrayList<>(newMarbles.subList(0, Math.min(newMarbles.size(), N * N - 1)));
        marbles.add(0, -1);
    }
}