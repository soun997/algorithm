import java.util.Scanner;

public class Main {

    static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        hanoi(1, 3, n);
        System.out.println((int)Math.pow(2, n) - 1);
        System.out.println(sb.toString());
    }

    static void hanoi(int a, int b, int n){

        if (n == 1) {
            sb.append(a).append(" ").append(b).append("\n");
            return;
        }

        hanoi(a, 6-b-a, n-1);
        sb.append(a).append(" ").append(b).append("\n");
        hanoi(6-b-a, b, n-1);
    }
}
