import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] matrix;
    static int[] line;
    static int[] move;
    static int marbleLength;    // 구슬이 마지막으로 채워져 있는 index
    static int[] explodedMarbles = new int[4];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] init = {7, 3, 1, 5};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][N];
        line = new int[N * N];
        move = new int[N * N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        fillLine();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            blizzard(d - 1, s);
//            System.out.println(Arrays.toString(line));
//            System.out.println(marbleLength);
        }

//        System.out.println(Arrays.toString(explodedMarbles));
        System.out.println(explodedMarbles[1] + 2 * explodedMarbles[2] + 3 * explodedMarbles[3]);
    }

    static void blizzard(int d, int s) {

        int idx = init[d];
        int delta = idx + 8;
        int removed = 0;
        for (int i = 0; i < s; i++) {
            if (line[idx] == 0) {
                break;
            }
            line[idx] = 0;
            updateStatus(idx + 1, 1);
            idx = idx + delta;
            delta = delta + 8;
            removed++;
        }
        removeZeros();
        marbleLength -= removed;
        while(explode());
        change();
    }

    static boolean explode() {

        int exploded = 0;
        for (int i = 1; i <= marbleLength;) {

            int cnt = count(i, line[i]);
            if (cnt >= 4) {
                explodedMarbles[line[i]] += cnt;
                for (int j = i; j < i + cnt; j++) {
                    line[j] = 0;
                }
                updateStatus(i + cnt, cnt);
                exploded += cnt;
            }
            i += cnt;
        }
        removeZeros();
        marbleLength -= exploded;
        return exploded != 0;
    }

    static void change() {

        int idx = 1;
        int[] newLine = new int[N * N];
        for (int i = 1; i <= marbleLength;) {
            int count = count(i, line[i]);
            if (idx >= N * N) {
                break;
            }
            newLine[idx++] = count;
            if (idx >= N * N) {
                break;
            }
            newLine[idx++] = line[i];
            i += count;
        }

        line = newLine;
        marbleLength = idx - 1;
    }

    static int count(int start, int num) {

        int cnt = 0;
        for (int i = start; i <= marbleLength; i++) {
            if (num == line[i]) {
                cnt++;
                continue;
            }
            break;
        }
        return cnt;
    }

    static void removeZeros() {
        for (int i = 1; i <= marbleLength; i++) {
            line[i - move[i]] = line[i];
            move[i] = 0;
        }
    }

    static void updateStatus(int start, int delta) {

        for (int i = start; i <= marbleLength; i++) {
            move[i] += delta;
        }
    }

    static void fillLine() {
        int[] dx = { 0, -1, 0, 1};
        int[] dy = { 1, 0, -1, 0};

        int length = 2;

        line[1] = matrix[(N + 1) / 2 - 1][(N + 1) / 2 - 2];
        line[2] = matrix[(N + 1) / 2][(N + 1) / 2 - 2];
        if (line[1] == 0) {
            return;
        }
        if (line[2] == 0) {
            length = 1;
        } else {
            length = 2;
        }

        int idx = 3;
        int r = (N + 1) / 2;
        int c = (N + 1) / 2 - 2;

        while(true) {

            for (int d = 0; d < 2; d++) {
                for (int i = 0; i < length; i++) {
                    r = r + dx[d];
                    c = c + dy[d];
                    if (isOutOfBounds(r, c)) {
                        marbleLength = idx - 1;
                        return;
                    }
                    line[idx++] = matrix[r][c];
                }
            }
            length++;
            for (int d = 2; d < 4; d++) {
                for (int i = 0; i < length; i++) {
                    r = r + dx[d];
                    c = c + dy[d];
                    if (isOutOfBounds(r, c)) {
                        marbleLength = idx - 1;
                        return;
                    }
                    line[idx++] = matrix[r][c];
                }
            }
            length++;
        }
    }

    static boolean isOutOfBounds(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N || matrix[x][y] == 0) {
            return true;
        }
        return false;
    }
}