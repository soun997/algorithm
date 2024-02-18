import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static int[] alphabets;
    static boolean[] visited;
    static Set<Character> vowels = Set.of('a', 'i', 'o', 'u', 'e');
    static StringBuilder result = new StringBuilder();

    // 한 개 이상의 모음, 두 개 이상의 자음, 알파벳은 정렬
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabets = new int[C];
        visited = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alphabets);
        combination(0, 0);
        System.out.println(result);
    }

    static void combination(int cnt, int idx) {

        if (cnt == L) {

            if (isPossible()) {
                for (int i = 0; i < visited.length; i++) {
                    if (visited[i]) {
                        result.append(((char)alphabets[i]));
                    }
                }
                result.append("\n");
            }
            return;
        }

        for (int i = idx; i < C; i++) {

            visited[i] = true;
            combination(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    static boolean isPossible() {

        int cnt = 0;
        for (int i = 0; i < visited.length; i++) {

            if (visited[i] && vowels.contains((char)alphabets[i])) {
                cnt++;
            }
        }

        return (cnt >= 1) && (L - cnt >= 2);
    }
}