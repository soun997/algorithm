import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int D;
    static int W;
    static int K;
    static int map[][];
    static int count;
    static int A[];
    static int B[];
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            count = Integer.MAX_VALUE;
            map = new int [D][W];
            A = new int[W];
            B = new int [W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < W; i++) {
                B[i] = 1;
            }

            subset(0,0);

            sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }
    static void subset(int cnt, int k) {
        if(k >= count) return;
        if(cnt == D) {
            if(check()) {
                count = Math.min(count, k);
            }
            return;
        }

        int temp[] = new int[W];
        temp = map[cnt];

        subset(cnt+1, k);

        map[cnt] = A;
        subset(cnt+1, k+1);

        map[cnt] = B;
        subset(cnt+1, k+1);

        map[cnt] = temp;

    }


    static boolean check() {
        boolean pass[] = new boolean[W];

        int copymap[][] = new int[W][D];
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < D; j++) {
                copymap[i][j] = map[j][i];
            }
        }

        int cnt = 1;
        for (int i = 0; i < W; i++) {
            cnt =1;
            for (int j = 0; j < D-1; j++) {
                if(cnt >= K) {
                    pass[i] = true;
                    break;
                }
                if(copymap[i][j] == copymap[i][j+1]) {
                    cnt++;
                }else {
                    cnt =1;
                }

            }
            if (cnt >= K) {
                pass[i] = true;
            }
        }
        for (int i = 0; i < W; i++) {
            if(!pass[i]) {
                return false;
            }
        }
        return true;
    }

}