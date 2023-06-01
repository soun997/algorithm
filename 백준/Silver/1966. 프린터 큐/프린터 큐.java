import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 프린터 큐
public class Main {

    static int N;
    static int target;
    static int[] priorities;
    static int[] documents; // 각 우선순위를 가지고 있는 문서 개수
    static Queue<Integer> printer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());
            priorities = new int[N];
            documents = new int[10];
            printer = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                printer.offer(i);
                priorities[i] = Integer.parseInt(st.nextToken());
                documents[priorities[i]]++;
            }

            int count = 1;
            while (!printer.isEmpty()) {
                int document = printer.poll();
                // 출력 불가한 우선순위라면 -> 다시 큐로
                if (!isValid(priorities[document])){
                    printer.offer(document);
                    continue;
                }

                if (document == target) {
                    result.append(count).append("\n");
                    break;
                }
                count++;
            }
        }
        System.out.println(result);
    }

    static boolean isValid(int priority){
        for (int i = 9; i >= 1; i--) {
            // 출력 가능한 우선순위
            if (priority == i){
                documents[priority]--;
                return true;
            }
            // 해당 문서보다 큰 우선순위가 있는지 확인
            if (documents[i] > 0){
                return false;
            }
        }
        return false;   // 문제의 조건 상 여기까지 올 일은 없다
    }
}