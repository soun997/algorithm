import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Main {

    static int n;
    static int r;
    static int c;
    static int result = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        visit(0, 0, 1 << n, 0);
    }

    static void visit(int x, int y, int n, int result) {
        if (n == 1) {
            if (x == r && y == c){
                System.out.println(result);
                return;
            }
            result++;
        }
        else {
            // 재귀를 다 돌 필요가 없으니, 어떤 상황에서 돌아야 할까?
            // (0, 0) -> (n/2, n/2)
            if (r >= x && r < x + n / 2 && c >= y && c < y + n / 2){
                visit(x, y, n / 2, result);
            }
            if (r >= x && r < x + n / 2 && c >= y + n / 2 && c < y + n){
                visit(x, y + n / 2, n / 2, result += n / 2 * n / 2 * 1);
            }
            if (r >= x + n / 2 && r < x + n && c >= y && c < y + n / 2){
                visit(x + n / 2, y, n / 2, result += n / 2 * n / 2 * 2);
            }
            if (r >= x + n / 2 && r < x + n && c >= y + n / 2 && c < y + n){
                visit(x + n / 2, y + n / 2, n / 2, result += n / 2 * n / 2 * 3);
            }
        }
    }
}

