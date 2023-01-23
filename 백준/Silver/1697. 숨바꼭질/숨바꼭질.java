import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    
    public static final int MAX = 100000;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();
        int[] line = new int[MAX + 2];
        Arrays.fill(line, -1);
        line[n] = 0;
        q.add(n);
        while(line[k] == -1){
            int cur = q.poll();

            for (int next : new int[]{cur - 1, cur + 1, 2 * cur}){
                if (next < 0 || next > MAX) continue;
                if (line[next] != -1) continue;
                line[next] = line[cur] + 1;
                q.add(next);
            }

        }
        System.out.println(line[k]);
    }
}
