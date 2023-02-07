import java.util.Scanner;

public class Main {

    static int N, M;
    static int[] p;
    static int[] nums;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        p = new int[N];
        nums = new int[M];
        visited = new boolean[N];
        for (int i = 0; i < N; i++){
            p[i] = i + 1;
        }
        perm(0);
        System.out.println(sb);
    }

    static void perm(int cnt){

        if (cnt == M){
            cnt++;
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            nums[cnt] = p[i];
            perm(cnt + 1);
            //nums[cnt] = 0;
            visited[i] = false;
        }
    }
}
