import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_BUTTONS = 10;
    static int n;
    static int broken;
    static Set<String> buttons;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        broken = Integer.parseInt(br.readLine());
        buttons = new HashSet<>();
        min = Integer.MAX_VALUE;

        for (int i = 0; i < MAX_BUTTONS; i++){
            buttons.add(String.valueOf(i));
        }
        if (broken != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < broken; i++){
                buttons.remove(st.nextToken());
            }
        }

        min = Math.min(min, Math.abs(100 - n));

        if (!buttons.isEmpty()) {
            find("");
        }

        System.out.println(min);
    }

    static void find(String num) {
        for (String btn : buttons) {
            String temp = num + btn;
            min = Math.min(min, Math.abs(n - Integer.parseInt(temp)) + temp.length());

            if (temp.length() < 6) {
                find(temp);
            }
        }
    }
}

// [출처] https://blog.naver.com/occidere/221354997206