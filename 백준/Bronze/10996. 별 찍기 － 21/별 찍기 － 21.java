import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n * 2; i++){
            for (int j = 0; j < n; j++){
                if (i % 2 == 0){
                    if (j % 2 == 0) sb.append("*");
                    else sb.append(" ");
                }
                else {
                    if (j % 2 == 0) sb.append(" ");
                    else sb.append("*");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
