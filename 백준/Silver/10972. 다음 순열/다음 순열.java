import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] sequence;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sequence = new int[N + 1];
        visited = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            visited[sequence[i]] = true;
        }

        StringBuilder sb = new StringBuilder();
        if (getNextSequence()){
            for (int i = 1; i <= N; i++) {
                sb.append(sequence[i]).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }
        System.out.println(-1);
    }

    static boolean getNextSequence() {

        boolean flag = false;

        for (int i = N; i >= 2; i--) {
            visited[sequence[i]] = false;
            if (sequence[i] > sequence[i - 1]) {
                visited[sequence[i - 1]] = false;
                sequence[i - 1]++;
                for (int j = sequence[i - 1]; j <= N; j++) {
                    if (visited[j]){
                        continue;
                    }
                    sequence[i - 1] = j;
                    break;
                }
                if (sequence[i - 1] > N){
                    return false;
                }
                visited[sequence[i - 1]] = true;
                makeNextSequence(i);
                flag = true;
                break;
            }
        }

        return flag;
    }

    static void makeNextSequence(int idx) {
        for (int i = 1; i <= N; i++){
            if (visited[i]){
                continue;
            }
            sequence[idx++] = i;
            if (idx > N){
                break;
            }
        }
    }
}