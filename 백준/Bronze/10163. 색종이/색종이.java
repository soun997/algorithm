import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int[][] papers;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        papers = new int[1001][1001];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            for (int j = a; j < a + c; j++){
                for (int k = b; k < b + d; k++){
                    papers[j][k] = i + 1;
                }
            }
        }

        int[] areas = new int[n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1001; i++){
            for (int j = 0; j < 1001; j++) {
                if (papers[i][j] != 0 && papers[i][j] != -1){
                    areas[papers[i][j] - 1]++;
                }
            }
        }
        for (int i = 0; i < areas.length; i++) {
            sb.append(areas[i]).append("\n");
        }
        System.out.println(sb);
    }
}
