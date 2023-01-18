import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(pow(a, b, c));
        //System.out.println(pow2(a, b, c));
    }

    // O(logb)
    static long pow(long a, long b, long m){

        if (b == 0) return 1;
        // 거듭제곱 수를 둘로 나눈다
        // -> b가 홀수 혹은 짝수냐에 따라 완전히 반으로 나누어지지 않을 수도 있다.
        long val = pow(a, b / 2, m);
        val = val * val % m;
        if (b % 2 == 0) return val;
        // 홀수일 경우 필연적으로 완벽히 둘로 나눠질 수 없다.
        // -> 그러므로 a를 하나 더 곱해줘야 한다. a가 m보다 클 수도 있으므로 a mod m을 해준다.
        else return val * a % m;
    }

    // O(b) -> b가 20억일 경우 무조건 시간초과가 난다.
    static long pow2(long a, long b, long m){
        long val = 1;
        while (b-- > 0) val = val * a % m;
        return val;
    }
}
