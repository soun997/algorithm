import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// KMP 알고리즘(Knuth–Morris–Pratt Algorithm)
// O(N+M)

/**
 * @author taeheekim
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] text = in.readLine().toCharArray();
        char[] pattern = in.readLine().toCharArray();

        int tLength = text.length, pLength = pattern.length;

        // 부분일치테이블 만들기 : KMP의 아이디어를 똑같이 적용, W에서 W를 찾는 듯한 행위를 해서...
        int[] pi = new int[pLength];
        // i는 접미사 포인터, j 는 접두사 포인터, i는 pattern의 길이만큼 이동
        for (int i = 1, j = 0; i < pLength; i++) {

            // j가 0이라면 ArrayIndexOutOfBounds 예외, j > 0 일 때에만
            while (j > 0 && pattern[i] != pattern[j]) {
                // pattern[i]와 pattern[j]가 다르다? -> 접두사/접미사가 같지 않음을 의미
                j = pi[j - 1];  // 이전의 일치하는 접두사/접미사 길이로 돌아감
            }

            // pattern[i]와 pattern[j]가 같다면 -> 접두사, 접미사 일치! partialTable에 ++해줌
            if (pattern[i] == pattern[j]) pi[i] = ++j;
                //
            else pi[i] = 0;
        }

        int cnt = 0;
        // i : 텍스트 포인터 , j: 패턴 포인터
        for (int i = 0, j = 0; i < tLength; ++i) {

            while (j > 0 && text[i] != pattern[j]) j = pi[j - 1];

            if (text[i] == pattern[j]) { //두 글자 일치
                if (j == pLength - 1) { // j가 패턴의 마지막 인덱스라면
                    cnt++; // 카운트 증가
                    sb.append(i - j + 1).append(" ");
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(sb);
    }
}