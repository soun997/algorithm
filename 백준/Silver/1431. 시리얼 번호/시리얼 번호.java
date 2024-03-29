import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N;
    static PriorityQueue<Guitar> q = new PriorityQueue<>();

    static class Guitar implements Comparable<Guitar> {

        String serial;

        public Guitar(String serial) {
            this.serial = serial;
        }

        @Override
        public int compareTo(Guitar other) {
            if (this.serial.length() == other.serial.length()) {
                int totalA = 0;
                int totalB = 0;
                for (int i = 0; i < this.serial.length(); i++) {
                    if (Character.isDigit(this.serial.charAt(i))) {
                        totalA += Character.getNumericValue(this.serial.charAt(i));
                    }
                    if (Character.isDigit(other.serial.charAt(i))) {
                        totalB += Character.getNumericValue(other.serial.charAt(i));
                    }
                }
                if (totalA == totalB) {
                    return this.serial.compareTo(other.serial);
                }
                return Integer.compare(totalA, totalB);
            }
            return Integer.compare(this.serial.length(), other.serial.length());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            q.offer(new Guitar(br.readLine()));
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Guitar g = q.poll();
            sb.append(g.serial).append("\n");
        }
        System.out.println(sb);
    }
}