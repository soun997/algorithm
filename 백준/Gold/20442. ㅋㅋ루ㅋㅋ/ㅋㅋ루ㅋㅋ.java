import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] kukuru = br.readLine().toCharArray();

        List<Integer> leftCount = new ArrayList<>();
        List<Integer> rightCount = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < kukuru.length; i++) {
            if (kukuru[i] == 'K') {
                count++;
                continue;
            }
            leftCount.add(count);
        }

        count = 0;
        for (int i = kukuru.length - 1; i >= 0; i--) {
            if (kukuru[i] == 'K') {
                count++;
                continue;
            }
            rightCount.add(count);
        }
        Collections.reverse(rightCount);

        int sum = 0;
        int left = 0;
        int right = rightCount.size() - 1;
        while (left <= right) {

            sum = Math.max(sum,
                    (right - left + 1) +
                            Math.min(leftCount.get(left), rightCount.get(right)) * 2
            );

            if (leftCount.get(left) < rightCount.get(right)){
                left++;
            } else {
                right--;
            }
        }

        System.out.println(sum);
    }
}
