import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Meeting implements Comparable {

        public long start;
        public long end;

        public Meeting(long start, long end){
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(Object o) {
            Meeting other = (Meeting)o;
            if (this.end < other.end){
                return -1;
            }
            else if(this.end == other.end){
                return Long.compare(this.start, other.start);
            }
            else
                return 1;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Meeting> timetable = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            timetable.add(new Meeting(start, end));
        }

        long cnt = 0;
        Meeting prev = timetable.poll();
        cnt++;
        while (!timetable.isEmpty()) {
            Meeting cur = timetable.poll();
            if (cur.start < prev.end) continue;
            prev = cur;
            cnt++;
        }

        System.out.println(cnt);
    }
}