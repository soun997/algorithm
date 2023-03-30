import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] students;
    static boolean[] visited;
    static boolean[] visitedCycle;
    static int total;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            students = new int[n + 1];
            visited = new boolean[n + 1];
            visitedCycle = new boolean[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            total = 0;
            for (int i = 1; i <= n; i++) {
                if (visited[i]){
                    continue;
                }
                find(i, i, 0);
            }
            sb.append(n - total).append("\n");
        }
        System.out.println(sb);
    }

    static boolean find(int x, int start, int count){
        // 시작지점 외의 싸이클이 생겨서 종료하는 경우
        if (visited[x]){
            if (!visitedCycle[x]){
                checkCycle(x, 0);
            }
            return true;
        }

        visited[x] = true;
        visitedCycle[x] = find(students[x], start, count + 1);
        return true;
    }

    static boolean checkCycle(int x, int count){
        if (visitedCycle[x]){
            total = total + count;
            return true;
        }

        visitedCycle[x] = true;
        checkCycle(students[x], count + 1);
        return true;
    }
}