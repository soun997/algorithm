import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int home = Integer.parseInt(st.nextToken());
            int office = Integer.parseInt(st.nextToken());
            if (home > office){
                int temp = home;
                home = office;
                office = temp;
            }
            people.add(new Person(home, office));
        }
        // end가 작은 순으로 sort
        Collections.sort(people, Comparator.comparingLong(o -> o.end));
        int D = Integer.parseInt(br.readLine());

        int max = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (Person person : people) {
            // 선분 길이보다 두 점 사이의 거리가 길다면
            if (person.end - person.start > D){
                continue;
            }

            pq.offer(person.start);
            while (!pq.isEmpty()) {
                if (person.end - pq.peek() <= D){
                    break;
                }
                pq.poll();
            }
            max = Math.max(max, pq.size());
        }

        System.out.println(max);
    }

    static class Person {
        long start;
        long end;

        public Person(int home, int office) {
            this.start = home;
            this.end = office;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "home=" + start +
                    ", office=" + end +
                    '}';
        }
    }
}