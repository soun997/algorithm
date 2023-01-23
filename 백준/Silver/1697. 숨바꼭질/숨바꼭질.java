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
            int n1 = cur - 1;
            int n2 = cur + 1;
            int n3 = 2 * cur;

            if (!(n1 < 0) && !(n1 > MAX)){
                if (line[n1] == -1){
                    line[n1] = line[cur] + 1;
                    q.add(n1);
                }
            }

            if (!(n2 < 0) && !(n2 > MAX)){
                if (line[n2] == -1){
                    line[n2] = line[cur] + 1;
                    q.add(n2);
                }
            }

            if (!(n3 < 0) && !(n3 > MAX)){
                if (line[n3] == -1){
                    line[n3] = line[cur] + 1;
                    q.add(n3);
                }
            }
        }
        System.out.println(line[k]);
    }
}
