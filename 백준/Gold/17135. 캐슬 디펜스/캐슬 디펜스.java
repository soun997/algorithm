import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX = 3;
    static int n, m;
    static int d;
    static List<Enemy> enemies;
    static List<Enemy> copied;
    static Set<Enemy> killed;
    static int cnt;
    static int max;
    static int[] p;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        enemies = new ArrayList<>();
        max = Integer.MIN_VALUE;
        p = new int[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                if (Integer.parseInt(st.nextToken()) == 1){
                    enemies.add(new Enemy(i, j, 0, false));
                }
            }
        }
        // 경우의 수 탐색을 위한 setting
        for (int i = 0; i < 3; i++){
            p[(m - 1) - i] = 1;
        }

        do {
            cnt = 0;
            copied = copy();
            while (!checkEnemies()){
                killed = new HashSet<>();
                for (int i = 0; i < m; i++) {
                    if (p[i] == 1){
                        attack(i);
                    }
                }
                killAll();
                moveEnemies();
            }
            max = Math.max(max, cnt);
        } while(nextCombination(m - 1));
        System.out.println(max);
    }

    static void attack(int col){

        PriorityQueue<Enemy> kills = new PriorityQueue<>();
        for (Enemy enemy : copied){
            if (enemy.canKill(col)){
                kills.offer(enemy);
            }
        }
        if (!kills.isEmpty()){
            killed.add(kills.peek());
        }
    }

    static void killAll(){
        for (Enemy enemy : killed){
            enemy.kill();
        }
        cnt += killed.size();
    }

    static boolean checkEnemies(){
        boolean isAllDead = true;
        for (Enemy enemy : copied){
            if (!enemy.isDead){
                isAllDead = false;
                break;
            }
        }
        return isAllDead;
    }

    static void moveEnemies(){
        for (Enemy enemy : copied){
            enemy.move();
        }
    }

    static List<Enemy> copy(){
        List<Enemy> copied = new ArrayList<>();
        for (Enemy e : enemies){
            Enemy enemy = new Enemy(e.x, e.y, 0, false);
            copied.add(enemy);
        }
        return copied;
    }

    static boolean nextCombination(int size){
        int i = size;
        while (i > 0 && p[i - 1] >= p[i])
            i--;
        if (i == 0)
            return false;

        int j = size;
        while (p[i - 1] >= p[j])
            j--;
        int temp = p[j];
        p[j] = p[i - 1];
        p[i - 1] = temp;

        int k = size;
        while (i < k) {
            temp = p[i];
            p[i] = p[k];
            p[k] = temp;
            i++;
            k--;
        }
        return true;
    }

    static class Enemy implements Comparable<Enemy> {
        int x;
        int y;
        int distance;
        boolean isDead;

        public Enemy(int x, int y, int distance, boolean isDead){
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.isDead = isDead;
        }

        public boolean canKill(int col){
            // 아직 죽지 않았다면
            if (!this.isDead){
                this.distance = Math.abs(this.x - n) + Math.abs(this.y - col);
                if (this.distance <= d){
                    return true;
                }
            }
            return false;
        }

        public void kill(){
            this.isDead = true;
        }


        public void move(){
            if (x + 1 >= n){
                kill();
                return;
            }
            x++;
        }

        @Override
        public int compareTo(Enemy other) {
            // 궁수와의 거리가 짧을 수록 높은 우선순위
            if (this.distance < other.distance){
                return -1;
            }
            // 궁수와의 거리가 같은 애들 -> 왼쪽에 있을 수록 높은 우선순위
            else if (this.distance == other.distance){
                if (this.y < other.y){
                    return -1;
                }
                else if (this.y == other.y){
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else {
                return 1;
            }
        }
    }
}