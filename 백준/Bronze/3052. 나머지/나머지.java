import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] nums = new int[43];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            nums[sc.nextInt() % 42]++;
        }
        int cnt = 0;
        for (int n : nums){
            if (n != 0) cnt++;
        }
        System.out.println(cnt);
    }
}
