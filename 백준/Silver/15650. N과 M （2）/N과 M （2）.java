import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] p;
    static int n;
    static int m;
    static int[] nums;
    static boolean [] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n];
        nums = new int[m];
        visited = new boolean[n];
        for (int i = 0; i < n; i++){
            p[i] = i + 1;
        }
        combi(0, 0);
    }

    static void combi(int cnt, int start) {
        if(cnt == m) {
            for (int i = 0; i < m; i++) {
                System.out.printf(nums[i]+" ");
            }
            System.out.println();
            return;
        }
        for (int i = start; i < n; i++) {
            visited[i]=true;
            nums[cnt]=p[i];
            combi(cnt + 1, i + 1);
            nums[cnt]=0;
            visited[i]=false;
        }
    }
}