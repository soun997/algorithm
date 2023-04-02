import java.util.Scanner;

public class Main {

    static int[] nums = new int[10];
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int mul = a * b * c;
        while (mul > 0){
            nums[mul % 10]++;
            mul = mul / 10;
        }
        for (int n : nums){
            System.out.println(n);
        }
    }
}
