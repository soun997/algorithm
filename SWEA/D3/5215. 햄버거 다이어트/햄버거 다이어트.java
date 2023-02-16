import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static class Ingredient {

        public int taste;
        public int calo;

        public Ingredient(int taste, int calo){
            this.taste = taste;
            this.calo = calo;
        }
    }

    static int n;
    static int l;
    static Ingredient[] ingredients;
    static boolean[] visited;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cases = Integer.parseInt(br.readLine());
        for (int t = 0; t < cases; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            ingredients = new Ingredient[n];
            visited = new boolean[n];
            max = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int taste = Integer.parseInt(st.nextToken());
                int calo = Integer.parseInt(st.nextToken());
                ingredients[i] = new Ingredient(taste, calo);
            }

            subSet(0, 0, 0);

            sb.append("#").append(t + 1).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static void subSet(int cnt, int taste, int calo){

        if (cnt == n){
            if (calo <= l)
                max = Math.max(max, taste);
            return;
        }

        visited[cnt] = true;
        subSet(cnt + 1
                , taste + ingredients[cnt].taste
                , calo + ingredients[cnt].calo);
        visited[cnt] = false;
        subSet(cnt + 1, taste, calo);
    }
}