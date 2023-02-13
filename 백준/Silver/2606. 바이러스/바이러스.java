import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) list.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            list.get(v1).add(v2);
            list.get(v2).add(v1);
        }

        int head = 1;
        Stack<Integer> stk = new Stack<>();
        stk.add(head);
        boolean[] infected = new boolean[n+1];
        infected[head] = true;
        int cnt = 0;
        while (!stk.isEmpty()) {
            head = stk.pop();
            for (int i = 0; i < list.get(head).size(); i++) {
                int computer = list.get(head).get(i);
                if (!infected[computer]) {
                    stk.add(computer);
                    infected[computer] = true;
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}