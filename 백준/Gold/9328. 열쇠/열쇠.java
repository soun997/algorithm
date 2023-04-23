import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int H, W;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] keys;
    static int count;

    static Queue<int[]> q = new ArrayDeque<>();
    static Map<Character, List<int[]>> doors = new HashMap<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            init();

            for (int i = 1; i <= H; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    map[i][j + 1] = input[j];
                }
            }

            char[] equipped = br.readLine().toCharArray();
            for (int i = 0; i < equipped.length; i++) {
                if (equipped[i] == '0'){
                    continue;
                }
                keys[equipped[i] - 97] = true;
            }
            bfs(0, 0);
            result.append(count).append("\n");
        }

        System.out.println(result);
    }


    static void bfs(int x, int y){

        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (map[cur[0]][cur[1]] == '$'){
                count++;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (isOutOfBound(nx, ny) || map[nx][ny] == '*' || visited[nx][ny]){
                    continue;
                }
                // 문이지만, 키가 없는 상태
                if (Character.isUpperCase(map[nx][ny]) && !keys[map[nx][ny] - 65]){
                    // 언젠가는 키를 찾을 수도 있으므로 일단 저장
                    doors.get(map[nx][ny]).add(new int[]{nx, ny});
                    continue;
                }
                // 열쇠 -> 줍줍
                if (Character.isLowerCase(map[nx][ny])){
                    keys[map[nx][ny] - 97] = true;
                    List<int[]> savedDoors = doors.get(Character.toUpperCase(map[nx][ny]));
                    // 해당 열쇠로 열 수 있는 문이 있다면
                    if (savedDoors.size() > 0){
                        for (int[] door : savedDoors){
                            q.offer(door);
                        }
                    }
                }
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    static boolean isOutOfBound(int x, int y){
        if (x < 0 || x >= H + 2 || y < 0 || y >= W + 2){
            return true;
        }
        return false;
    }

    static void init(){
        map = new char[H + 2][W + 2];
        Arrays.fill(map[0], '.');
        Arrays.fill(map[H + 1], '.');
        for (int i = 0; i < H; i++) {
            map[i][0] = '.';
        }
        for (int i = 0; i < H; i++) {
            map[i][W + 1] = '.';
        }
        visited = new boolean[H + 2][W + 2];
        keys = new boolean[26];

        q.clear();
        doors.clear();
        for (int i = 0; i < 26; i++) {
            doors.put((char)(i + 65), new ArrayList<>());
        }

        count = 0;
    }

}