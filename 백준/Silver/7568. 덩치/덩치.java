import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Body {

        int height;
        int weight;

        public Body(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
    }

    static int N;
    static Body[] bodies;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        bodies = new Body[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            bodies[i] = new Body(height, weight);
        }

        for (int i = 0; i < N; i++) {
            Body origin = bodies[i];
            int greater = 0;
            for (int j = 0; j < N; j++) {
                Body target = bodies[j];
                // 덩치가 더 큰 사람 존재
                if (origin.height < target.height && origin.weight < target.weight) {
                    greater++;
                }
            }
            answer.append(greater + 1).append(" ");
        }

        System.out.println(answer);
    }
}