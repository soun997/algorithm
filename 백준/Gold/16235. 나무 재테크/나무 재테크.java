import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int BABY_TREE = 1;
    static int N, M, K;
    static int[][] A;
    static int[][] ground;
    static PriorityQueue<Integer>[][] tree;
    static Queue<Integer>[][] deadTree;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        ground = new int[N][N];
        tree = new PriorityQueue[N][N];
        deadTree = new ArrayDeque[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
                tree[i][j] = new PriorityQueue<>();
                deadTree[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            tree[x][y].offer(age);
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count += tree[i][j].size();
            }
        }
        System.out.println(count);
    }

    static void spring() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> temp = new PriorityQueue<>();
                while (!tree[i][j].isEmpty()) {
                    // 해당 칸의 나무가 자신의 나이만큼 양분을 먹는다.
                    int treeAge = tree[i][j].poll();
                    if (ground[i][j] >= treeAge) {
                        ground[i][j] -= treeAge;
                        temp.offer(++treeAge);
                    } else {
                        // 양분을 못먹는 나무는 죽는다.
                        deadTree[i][j].offer(treeAge);
                    }
                }
                while (!temp.isEmpty()) {
                    tree[i][j].offer(temp.poll());
                }
            }
        }
    }

    static void summer() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while (!deadTree[i][j].isEmpty()) {
                    int food = deadTree[i][j].poll() / 2;
                    ground[i][j] += food;
                }
            }
        }
    }

    static void fall() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int breed : tree[i][j]) {
                    // 나이가 5의 배수인 나무만 번식한다.
                    if (breed % 5 != 0) {
                        continue;
                    }
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (isOutOfBounds(nx, ny)) {
                            continue;
                        }
                        tree[nx][ny].offer(BABY_TREE);
                    }
                }
            }
        }
    }

    static void winter() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] += A[i][j];
            }
        }
    }

    static boolean isOutOfBounds(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return true;
        }
        return false;
    }
}