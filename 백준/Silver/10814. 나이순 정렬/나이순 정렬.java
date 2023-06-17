import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Member implements Comparable<Member> {

        int id;
        int age;
        String name;

        public Member(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Member other) {
            if (this.age == other.age){
                return Integer.compare(this.id, other.id);
            }
            return Integer.compare(this.age, other.age);
        }
    }

    static int N;
    static PriorityQueue<Member> members;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        members = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members.offer(new Member(i, age, name));
        }

        StringBuilder answer = new StringBuilder();
        while (!members.isEmpty()) {
            Member member = members.poll();
            answer.append(member.age).append(" ").append(member.name).append("\n");
        }
        System.out.println(answer);
    }
}