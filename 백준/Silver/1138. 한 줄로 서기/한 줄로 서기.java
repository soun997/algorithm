import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {


public static void main(String[] args) throws Exception{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N = Integer.parseInt(br.readLine());
	int [] remember = new int[N];
	ArrayList <Integer> result = new ArrayList<>();

	StringTokenizer st = new StringTokenizer(br.readLine());
	for (int i = 0; i < N; i++) {
		remember[i] = Integer.parseInt(st.nextToken());
	}

    //뒤에서부터 i인덱스에 값 추가
	for (int i = N-1; i >= 0; i--) {
		result.add(remember[i],i);

	}
	for (Integer i : result) {
		System.out.print((i+1)+" ");
	}
}

}

