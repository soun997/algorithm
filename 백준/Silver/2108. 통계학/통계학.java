import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] numbers;
    static int average, median, mode, scope;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        int sum = 0;
        int[] frequency = new int[8001];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            sum = sum + numbers[i];

            // 음수일 경우
            if (numbers[i] < 0){
                frequency[Math.abs(numbers[i]) + 4000]++;
            } else {
                frequency[numbers[i]]++;
            }

            min = Math.min(min, numbers[i]);
            max = Math.max(max, numbers[i]);
        }

        // 산술평균
        average = Math.round((float) sum / N);

        // 중앙값 찾기
        Arrays.sort(numbers);
        median = numbers[N / 2];

        // 최빈값 찾기
        int maxFrequency = 0;
        for (int i = 0; i <= 8000; i++) {
            if (maxFrequency < frequency[i]) {
                maxFrequency = frequency[i];
            }
        }

        // 0 이하인 숫자 중 두번째로 작은 최빈값
        boolean flag = false;
        boolean isDone = false;
        for (int i = 8000; i > 4000; i--) {
            if (maxFrequency == frequency[i]) {
                mode = (i - 4000) * -1;
                if (flag){
                    isDone = true;
                    break;
                } else {
                    flag = true;
                }
            }
        }

        // 0 이상인 숫자들 중에서 최빈값
        if (!isDone){
            for (int i = 0; i <= 4000; i++) {
                if (maxFrequency == frequency[i]) {
                    mode = i;
                    if (flag){
                        break;
                    } else {
                        flag = true;
                    }
                }
            }

        }

        scope = max - min;

        System.out.println(average);
        System.out.println(median);
        System.out.println(mode);
        System.out.println(scope);
    }

}