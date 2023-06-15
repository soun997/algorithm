import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 먼저, 5kg으로 나눴을 때 남은 설탕을 3kg으로 나눌 수 없다면
        if ((N % 5) % 3 != 0){
            int a = 0;
            int b = 0;

            // 3키로 봉지로 만들 수 있는 수: 3, 6, 9, 12 -> 15는 3과 5의 최대공배수이므로 5로도 나누어짐!!
            // 고로 3키로씩 최대 4번 뺐을 때까지 답이 나오지 않는 다면 N킬로그램을 만들 수 없음
            for (int i = 0; i < 4; i++) {
                b++;    // 3키로짜리 봉지 추가
                N = N - 3;  // 3kg 하나 빼줌

                // N이 음수까지 내려갔다면 -> N킬로그램 만들기 불가
                if (N < 0){
                    break;
                }
                if ((N % 5) % 3 == 0){
                    a = N / 5;
                    b = b + (N % 5) / 3;
                    System.out.println(a + b);
                    return;
                }
            }
            System.out.println(-1);
            return;
        }

        int a = N / 5;
        int b = (N % 5) / 3;
        System.out.println(a + b);
    }
}