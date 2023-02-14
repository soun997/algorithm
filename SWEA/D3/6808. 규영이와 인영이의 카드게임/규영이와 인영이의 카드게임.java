import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int cases;
    static int[] cards;
    static int[] gyu;
    static int[] in;
    static boolean[] visited;
    static int[] choices;
    static int gyuWin;
    static int gyuLose;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        cases = Integer.parseInt(br.readLine());
        for (int t = 0; t < cases; t++) {
            cards = new int[18];
            gyu = new int[9];
            in = new int[9];
            visited = new boolean[9];
            choices = new int[9];
            gyuWin = 0;
            gyuLose = 0;

            // 규영이와 인영이에게 카드를 나눠준다.
            // 규영 -> 입력값 그대로 고정
            // 인영 -> 카드 순서 조작 가능, 그러나 규영이에게 있는 카드는 쓸 수 없다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                gyu[i] = Integer.parseInt(st.nextToken());
                cards[gyu[i] - 1]++;
            }

            int idx = 0;
            for (int i = 0; i < 18; i++){
                if (cards[i] != 0) continue;
                in[idx++] = i + 1;
            }

            permutation(0);

            sb.append("#").append(t + 1).append(" ").append(gyuWin).append(" ").append(gyuLose).append("\n");
        }
        System.out.println(sb);
    }
    static void permutation(int cnt){

        if (cnt == 9){
            int gyuScore = 0;
            int inScore = 0;
            for (int i = 0; i < 9; i++) {
                if (gyu[i] > choices[i]) gyuScore += gyu[i] + choices[i];
                if (gyu[i] < choices[i]) inScore += gyu[i] + choices[i];
            }
            if (gyuScore > inScore) gyuWin++;
            if (gyuScore < inScore) gyuLose++;
            return;
        }

        for (int i = 0; i < 9; i++){
            if (visited[i]) continue;
            visited[i] = true;
            choices[cnt] = in[i];
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}
