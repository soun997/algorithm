import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < alphabet.length(); i++) {
            char c = alphabet.charAt(i);
            if (s.indexOf(c) != -1) {
                System.out.print(s.indexOf(c) + " ");
            } else {
                System.out.print("-1 ");
            }
        }
        sc.close();
    }
}