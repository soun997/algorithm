import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Solution  {

    static int N, M, C;
    static int[][] map, maxMap;
    static int[] p;
    static int[] choices;
    static int max;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = null;
        for (int tc = 1; tc <= TC; ++tc) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken()); // 벌통 크기
            M = Integer.parseInt(st.nextToken()); // 선택할 벌통개수
            C = Integer.parseInt(st.nextToken()); // 벌꿀 채취량
            map = new int[N][N];
            maxMap = new int[N][N-M+1]; // r,c 위치에서 연속된 M개의 벌통을 놓았을 경우 최대 수익, 0으로 초기화된 상황
            p = new int[N];
            for (int i = 0; i < N; i++) {
                p[i] = i;
            }
            choices = new int[2];
            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            getMaxBenefit();
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static void getMaxBenefit() {
        makeMaxMap();   // 얻을 수 있는 수익의 최댓값들을 기록
        combination(0, 0);  // 행 두 개 조합으로 선택, 
    }
    private static void makeMaxMap() {
        for (int i = 0; i < N; ++i) { // 연속된 M개를 만들수 있는 위치마다 연속 M개로 만들수 있는 부분집합의 최대이익 계산
            for (int j = 0; j < N - M + 1; ++j) {
                // 01 12 23과 같이 다양한 열 위치에서 M만큼 연속하여 꿀 채집
                makeMaxSubset(i, j, 0, 0, 0);
            }
        }
    }
    private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {

        // 채취한 양이 채취가능한 양보다 많다면 -> return
        if (sum > C) return;
        // 무사히 꿀통에 있는 꿀들을 채취했다면
        if (cnt == M) {
            // 수익을 기록
            if (maxMap[i][j - M] < powSum)
                maxMap[i][j - M] = powSum;
            return;
        }
        // 해당 꿀통을 선택
        makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]);
        // 해당 꿀통을 비선택 (채취가능한 꿀통 중 일부만 채취가능한 경우)
        makeMaxSubset(i, j + 1, cnt + 1, sum, powSum);
    }

    // Combination 계산
    static void combination(int cnt, int start){

        if (cnt == 2){
            //System.out.println(Arrays.toString(choices));
            int result = 0;
            for (int i = 0; i < 2; i++) {
                int maxCol = 0;
                for (int j = 1; j < N - M + 1; j++){
                    if (maxMap[choices[i]][maxCol] < maxMap[choices[i]][j]){
                        maxCol = j;
                    }
                }
                result += maxMap[choices[i]][maxCol];
                //System.out.println(maxMap[choices[i]][maxCol]);
            }
            max = Math.max(max, result);
            return;
        }

        for (int i = start; i < N; i++) {
            choices[cnt] = p[i];
            combination(cnt + 1, i + 1);
        }
    }
}