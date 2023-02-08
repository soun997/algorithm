import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int l;
    static int c;
    static char[] words;
    static char[] choices;
    static char[] copied;
    static boolean[] visited;
    static Set<String> crypto = new TreeSet<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        words = new char[c];
        visited = new boolean[c];
        choices = new char[l];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++){
            words[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(words);

        combi(0, 0);

        for (String s : crypto){
            System.out.println(s);
        }
    }

    static void combi(int cnt, int start){

        if (cnt == l){
            //System.out.println(Arrays.toString(choices));
            int moCnt = 0;
            int jaCnt = 0;
            for (int i = 0; i < l; i++){
                if (
                        choices[i] == 'a' || choices[i] == 'i' || choices[i] == 'o'
                        || choices[i] == 'u' || choices[i] == 'e'
                ){
                    moCnt++;
                }
                else
                    jaCnt++;
            }
            if (moCnt < 1) return;
            if (jaCnt < 2) return;


            crypto.add(String.valueOf(choices));
            return;
        }

        for (int i = start; i < c; i++){
            choices[cnt] = words[i];
            combi(cnt + 1, i + 1);
        }
    }
}
