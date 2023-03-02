import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node {

        int idx;    // 도착 정점
        int cost;   // 도착 정점으로 가는 비용

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }

    static final int INFINITE = Integer.MAX_VALUE;  // 무한대 값

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E;    // 정점과 간선의 개수
    static int start;   // 출발 지점

    static ArrayList<Node>[] graph; // 인접 리스트
    static boolean[] visited;   // 노드 방문 여부 확인을 위한 재열
    static int[] distance;      // 출발 노드부터 도착 노드까지의 최소 거리를 저장하기 위한 배열

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        initGraph();    // 그래프 초기화

        makeGraph();    // 그래프 생성

        dijkstra();

        printResult();

        br.close();
    }

    // 그래프 초기화
    static void initGraph(){
        graph = new ArrayList[V + 1];   // 노드의 idx는 1 ~ V + 1번까지
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static void makeGraph() throws IOException {

        // 그래프 생성
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }
    }

    static void dijkstra(){

        visited = new boolean[V + 1];
        distance = new int[V + 1];

        // 최소 거리 정보를 담을 배열 초기화 -> 무한한 값으로
        Arrays.fill(distance, INFINITE);
        distance[start] = 0;    // 출발 지점은 0으로 초기화

        // 다익스트라 알고리즘
        // 모든 정점을 방문했다면 종료 (정점의 개수만큼 반복)
        for (int v = 1; v < V + 1; v++) {

            int minCost = INFINITE;
            int minIdx = -1;

            // 현재 정점에서의 거리 비용이 최소인 정점을 선택
            for (int i = 1; i < V + 1; i++) {
                if (!visited[i] && distance[i] < minCost){
                    minCost = distance[i];
                    minIdx = i;
                }
            }

            // 그래프가 끊어진 경우
            if (minIdx == -1){
                break;
            }

            // 최종적으로 선택된 정점을 방문처리
            visited[minIdx] = true;

            // 선택된 정점을 기준으로 인접한 정점의 최소 거리 값을 갱신
            for (Node node : graph[minIdx]){

                // 인접 정점이 현재 가지는 최소 비용 VS 현재 선택된 정점의 비용 + 현재 선택된 정점에서 인접 정점으로 가는 비용
                if (distance[node.idx] > distance[minIdx] + node.cost){
                    distance[node.idx] = distance[minIdx] + node.cost;
                }
            }
        }
    }

    static void printResult(){
        for (int i = 1; i < distance.length; i++) {
            sb.append(distance[i] == INFINITE ? "INF" : distance[i]).append("\n");
        }
        System.out.println(sb);
    }
}