import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        char[] explode = br.readLine().toCharArray();

        Stack<Character> origin = new Stack<>();
        Stack<Integer> indexes = new Stack();

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
                        cnt = indexes.pop();
                        continue;
                    }
                    cnt = 0;
                }
                continue;
            }

            if (origin.peek() == explode[0]){
                indexes.push(cnt);
                cnt = 1;
                continue;
            }

            while(!indexes.isEmpty()){
                indexes.pop();
            }
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
        System.out.println(sb.reverse());
    }
}