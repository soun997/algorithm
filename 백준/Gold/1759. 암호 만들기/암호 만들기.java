import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static int[] alphabets;
    static boolean[] visited = new boolean[26];
    static int[] vowels = { 'a', 'i', 'o', 'u', 'e' };
    static StringBuilder result = new StringBuilder();

    // 한 개 이상의 모음, 두 개 이상의 자음, 알파벳은 정렬
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabets = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken().toCharArray()[0];
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
                        result.append(((char)(i + 97)));
                    }
                }
                result.append("\n");
            }
            return;
        }

        for (int i = idx; i < C; i++) {

            visited[alphabets[i] - 97] = true;
            combination(cnt + 1, i + 1);
            visited[alphabets[i] - 97] = false;
        }
    }

    static boolean isPossible() {

        int cnt = 0;
        for (int i = 0; i < vowels.length; i++) {

            if (visited[vowels[i] - 97]) {
                cnt++;
            }
        }

        return (cnt >= 1) && (L - cnt >= 2);
    }
}