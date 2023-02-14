import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static class Tank {

        public int x;
        public int y;
        public int dir;

        public Tank(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void move(int dir){
            if (!isMovable(this.x + dx[dir], this.y + dy[dir])){
                this.dir = dir;
                map[this.x][this.y] = move[dir];
                return;
            }
            map[this.x][this.y] = '.';
            this.x += dx[dir];
            this.y += dy[dir];
            this.dir = dir;
            map[this.x][this.y] = move[dir];
        }

        private boolean isMovable(int x, int y){
            // 범위 밖이라면 이동 불가
            if (x < 0 || x >= h || y < 0 || y >= w)
                return false;
            // 평지라면 이동가능
            if (map[x][y] == '.')
                return true;
            return false;
        }
    }

    static int cases;
    static int h;
    static int w;
    static char[][] map;
    static int n;
    static char[] commands;
    static char[] move = {'^', '>', 'v', '<'};
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Tank tank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < cases; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];

            for (int i =0; i < h; i++){
                String[] inputs = br.readLine().split("");
                for (int j = 0; j < w; j++) {
                    map[i][j] = inputs[j].charAt(0);
                    init(i, j);
                }
            }
            n = Integer.parseInt(br.readLine());
            commands = new char[n];
            String[] inputs = br.readLine().split("");
            for (int i = 0; i < n; i++) {
                commands[i] = inputs[i].charAt(0);
                if (commands[i] != 'S')
                    move(commands[i]);
                else
                    shoot(tank);
            }

            sb.append("#").append(t + 1).append(" ");
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void init(int x, int y){

        for (int i = 0; i < 4; i++){
            if (map[x][y] == move[i]){
                tank = new Tank(x, y, i);
                return;
            }
        }
    }

    static void move(char command){
        switch(command){
            case 'U':
                tank.move(0);
                break;
            case 'R':
                tank.move(1);
                break;
            case 'D':
                tank.move(2);
                break;
            case 'L':
                tank.move(3);
                break;
        }
    }

    static void shoot(Tank tank){

        int[] bullet = {tank.x, tank.y};
        while (true){
            bullet[0] += dx[tank.dir];
            bullet[1] += dy[tank.dir];
            if (bullet[0] < 0 || bullet[0] >= h || bullet[1] < 0 || bullet[1] >= w)
                break;
            if (map[bullet[0]][bullet[1]] == '*'){
                map[bullet[0]][bullet[1]] = '.';
                break;
            }
            if (map[bullet[0]][bullet[1]] == '#'){
                break;
            }
        }
    }
}