import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] input = br.readLine().toCharArray();
        char[] explode = br.readLine().toCharArray();

        Stack<Character> origin = new Stack<>();
        List<Integer> indexes = new ArrayList<>();

        int idx = 0;
        int cnt = 0;
        while(idx < input.length){

            origin.push(input[idx++]);

            if (origin.peek() == explode[cnt]){
                cnt++;
                if (cnt == explode.length){
                    for (int i = 0; i < explode.length; i++) {
                        origin.pop();
                    }
                    if (!indexes.isEmpty()){
                        cnt = indexes.get(indexes.size() - 1);
                        indexes.remove(indexes.size() - 1);
                        continue;
                    }
                    cnt = 0;
                }
                continue;
            }

            if (origin.peek() == explode[0]){
                indexes.add(cnt);
                cnt = 1;
                continue;
            }

            indexes.clear();
            cnt = 0;
        }

        if (origin.size() == 0){
            System.out.println("FRULA");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while(!origin.isEmpty()){
            sb.append(origin.pop());
        }
        bw.write(sb.reverse().toString());
        bw.flush();
        br.close();
        bw.close();
    }
}