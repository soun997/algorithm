import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1x1 정사각형을 여러 개 이어서 붙인 도형 -> 4개 이어 붙임
 * 각각의 칸에는 점수
 * 테트로미노 하나를 적절히 놓아서 놓인 칸의 합이 제일 큰 경우
 */
public class Main {

    static int N, M;
    static int[][] board;   // 점수판
    static int[][][] blocks = {
            {{1, 1, 1, 1}},
            {{1, 1},
                    {1, 1}},
            {{1, 0},
                    {1, 0},
                    {1, 1}},
            {{1, 0},
                    {1, 1},
                    {0, 1}},
            {{1, 1, 1},
                    {0, 1, 0}}
    };

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < blocks.length; k++) {
                    // 회전 -> X 접기 -> Y 접기
                    for (int r = 0; r < 4; r++) {
                        blocks[k] = rotate(blocks[k]);
                        max = Math.max(max, getScore(i, j, blocks[k]));
                        max = Math.max(max, getScore(i, j, flipX(blocks[k])));
                        max = Math.max(max, getScore(i, j, flipY(blocks[k])));
                    }
                }
            }
        }

        System.out.println(max);
    }

    static int getScore(int x, int y, int[][] block) {
        int score = 0;
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                if (block[i][j] == 0) {
                    continue;
                }
                int nx = x + i;
                int ny = y + j;
                // 블록을 놓을 수 없는 경우
                if (isOutOfBounds(nx, ny)) {
                    return 0;
                }
                score += board[nx][ny];
            }
        }
        return score;
    }

    static boolean isOutOfBounds(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return true;
        }
        return false;
    }

    static int[][] rotate(int[][] block) {
        int[][] newBlock = new int[block[0].length][block.length];
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                newBlock[j][block.length - i - 1] = block[i][j];
            }
        }
//        for (int i = 0; i < newBlock.length; i++) {
//            System.out.println(Arrays.toString(newBlock[i]));
//        }
        return newBlock;
    }

    static int[][] flipX(int[][] block) {
        int[][] newBlock = new int[block.length][block[0].length];
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                newBlock[i][block[0].length - j - 1] = block[i][j];
            }
        }
        return newBlock;
    }

    static int[][] flipY(int[][] block) {
        int[][] newBlock = new int[block.length][block[0].length];
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                newBlock[block.length - i - 1][j] = block[i][j];
            }
        }
        return newBlock;
    }
}