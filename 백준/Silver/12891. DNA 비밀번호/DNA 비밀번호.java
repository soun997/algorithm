import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int s;
    static int p;
    static char[] dna;
    static int[] require;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        dna = br.readLine().toCharArray();
        require = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            require[i] = Integer.parseInt(st.nextToken());
        }

        validate();
        System.out.println(cnt);
    }

    static void validate(){

        Queue<Character> q = new LinkedList<>();
        int[] contained = new int[4];
        for (int i = 0; i < p; i++){
            q.offer(dna[i]);
            if (dna[i] == 'A') contained[0]++;
            else if (dna[i] == 'C') contained[1]++;
            else if (dna[i] == 'G') contained[2]++;
            else if (dna[i] == 'T') contained[3]++;


        }

        if ((contained[0] >= require[0]) && (contained[1] >= require[1]) &&
                (contained[2] >= require[2]) && (contained[3] >= require[3])){
            cnt++;
        }

        int cur = p - 1;

        while (cur <= s - 2){

            // 이전 것을 빼고
            char c = q.poll();
            if (c == 'A') contained[0]--;
            else if (c == 'C') contained[1]--;
            else if (c == 'G') contained[2]--;
            else if (c == 'T') contained[3]--;

            // 다음 것을 넣는다
            cur++;
            if (dna[cur] == 'A') contained[0]++;
            else if (dna[cur] == 'C') contained[1]++;
            else if (dna[cur] == 'G') contained[2]++;
            else if (dna[cur] == 'T') contained[3]++;
            else continue;
            q.offer(dna[cur]);

            // 유효한 부분 문자열인지
            if ((contained[0] >= require[0]) && (contained[1] >= require[1]) &&
                    (contained[2] >= require[2]) && (contained[3] >= require[3])){
                cnt++;
            }
        }
    }
}
