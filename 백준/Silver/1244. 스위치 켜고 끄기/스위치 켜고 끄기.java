import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] switches;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        switches = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int stdNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < stdNum; i++) {
            int[] input = new int[2];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                input[j] = Integer.parseInt(st.nextToken());
            }
            if (input[0] == 1){
                boy(input[1]);
            }
            else{
                girl(input[1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(switches[i]).append(" ");
            if ((i + 1) % 20 == 0)
                sb.append("\n");
        }
        System.out.println(sb);
    }

    static void boy(int switchNum){
        for (int i = 0; i < n; i++) {
            if ((i + 1) % switchNum == 0){
                change(i);
            }
        }
    }

    static void girl(int switchNum){
        change(switchNum - 1);
        int cnt = 1;
        while(true){
            if (switchNum - cnt - 1 < 0 || switchNum + cnt - 1 >= n) break;
            if (switches[switchNum - cnt - 1] == switches[switchNum + cnt - 1]){
                change(switchNum - cnt - 1);
                change(switchNum + cnt - 1);
            }
            else
                break;
            cnt++;
        }
    }

    static void change(int switchNum){
        switches[switchNum] = switches[switchNum] == 0 ? 1 : 0;
    }
}
