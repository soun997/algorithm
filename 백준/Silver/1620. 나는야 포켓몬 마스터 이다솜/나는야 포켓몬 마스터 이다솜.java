import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


// 나는야 포켓몬 마스터~
public class Main {

    static int N, M;
    static Map<String, String> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            map.put(String.valueOf(i), name);
            map.put(name, String.valueOf(i));
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            answer.append(map.get(input)).append("\n");
        }

        System.out.println(answer);
    }
}