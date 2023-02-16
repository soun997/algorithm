import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int r;
    static int[][] arr;
    static int[][] copied;
    static int[] dx;
    static int[] dy;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        dx = new int[]{0, m / 2, m / 2, 0};
        dy = new int[]{0, 0, n / 2, n / 2};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            execute(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void execute(int cmd){

        int temp = 0;
        switch (cmd){
            case 1:
                copied = new int[n][m];
                one();
                break;
            case 2:
                copied = new int[n][m];
                two();
                break;
            case 3:
                temp = n;
                n = m;
                m = temp;
                copied = new int[n][m];
                three();
                break;
            case 4:
                temp = n;
                n = m;
                m = temp;
                copied = new int[n][m];
                four();
                break;
            case 5:
                copied = new int[n][m];
                five();
                break;
            case 6:
                copied = new int[n][m];
                six();
                break;
        }
    }

    static void one(){

        for (int i = 0; i < n / 2; i++){
            copied[i] = arr[(n - 1) - i] ;
            copied[(n - 1) - i] = arr[i];
        }
        arr = copied;
    }
    static void two(){

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m / 2; j++){
                copied[i][j] = arr[i][(m - 1) - j];
                copied[i][(m - 1) - j] = arr[i][j];
            }
        }
        arr = copied;
    }

    static void three(){

        for (int i = 0; i < n; i++){
            int[] temp = new int[m];
            for (int j = 0; j < m; j++){
                temp[j] = arr[(m - 1) - j][i];
            }
            copied[i] = temp;
        }

        arr = copied;
    }

    static void four(){

        for (int i = n - 1; i >= 0; i--){
            int[] temp = new int[m];
            for (int j = 0; j < m; j++){
                temp[j] = arr[j][i];
            }
            copied[(n - 1) - i] = temp;
        }

        arr = copied;
    }

    static void five() {

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
/*                copied[i + dy[1]][j + dx[1]] = arr[i + dy[0]][j + dx[0]];
                copied[i + dy[2]][j + dx[2]] = arr[i + dy[1]][j + dx[1]];
                copied[i + dy[3]][j + dx[3]] = arr[i + dy[2]][j + dx[2]];
                copied[i + dy[0]][j + dx[0]] = arr[i + dy[3]][j + dx[3]];*/
                copied[i][j + m / 2] = arr[i][j]; // 1 -> 2
                copied[i + n / 2][j + m / 2] = arr[i][j + m / 2]; // 2 -> 3;
                copied[i + n / 2][j] = arr[i + n / 2][j + m / 2];    // 3 -> 4;
                copied[i][j] = arr[i + n / 2][j]; // 4 -> 1
            }
        }

        arr = copied;
    }

    static void six() {

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
/*                copied[i + dy[3]][j + dx[3]] = arr[i + dy[0]][j + dx[0]];
                copied[i + dy[0]][j + dx[0]] = arr[i + dy[1]][j + dx[1]];
                copied[i + dy[1]][j + dx[1]] = arr[i + dy[2]][j + dx[2]];
                copied[i + dy[2]][j + dx[2]] = arr[i + dy[3]][j + dx[3]];*/
                copied[i + n / 2][j] = arr[i][j]; // 1 -> 4
                copied[i][j] = arr[i][j + m / 2];    // 2 -> 1
                copied[i][j + m / 2] = arr[i + n / 2][j + m / 2];    // 3 -> 2
                copied[i + n / 2][j + m / 2] = arr[i + n / 2][j];
            }
        }

        arr = copied;
    }
}