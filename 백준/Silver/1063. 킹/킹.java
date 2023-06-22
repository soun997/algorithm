import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

// 킹
public class Main {

    static Map<String, Integer> col = Map.of(
            "A", 0,
            "B", 1,
            "C", 2,
            "D", 3,
            "E", 4,
            "F", 5,
            "G", 6,
            "H", 7
    );
    static Map<Integer, String> reverseCol = Map.of(
            0, "A",
            1, "B",
            2, "C",
            3, "D",
            4, "E",
            5, "F",
            6, "G",
            7, "H"
    );
    static Map<String, Integer> command = Map.of(
            "B", 0,
            "L", 1,
            "T", 2,
            "R", 3,
            "LB", 4,
            "RB", 5,
            "LT", 6,
            "RT", 7
    );

    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};

    static int[] king = new int[2];
    static int[] stone = new int[2];
    static int N;
    static int[][] board = new int[8][8];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String[] input1 = st.nextToken().split("");
        king[0] = col.get(input1[0]);
        king[1] = Integer.parseInt(input1[1]) - 1;
        board[king[1]][king[0]] = 1;

        String[] input2 = st.nextToken().split("");
        stone[0] = col.get(input2[0]);
        stone[1] = Integer.parseInt(input2[1]) - 1;
        board[stone[1]][stone[0]] = -1;

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String idx = br.readLine();
            move(king[1],  king[0], command.get(idx));
        }
        System.out.println(reverseCol.get(king[0]) + (king[1] + 1));
        System.out.println(reverseCol.get(stone[0]) + (stone[1] + 1));
    }

    static void move(int x, int y, int cmd) {
        int nx = x + dx[cmd];
        int ny = y + dy[cmd];
        // 범위 초과
        if (isOutOfBound(nx, ny)) {
            return;
        }
        // 돌
        if (board[nx][ny] == -1) {
            int snx = nx + dx[cmd];
            int sny = ny + dy[cmd];
            if (isOutOfBound(snx, sny)) {
                return;
            }
            board[snx][sny] = -1;
            stone[1] = snx;
            stone[0] = sny;
        }
        board[nx][ny] = 1;
        king[1] = nx;
        king[0] = ny;
    }

    static boolean isOutOfBound(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return true;
        }
        return false;
    }
}