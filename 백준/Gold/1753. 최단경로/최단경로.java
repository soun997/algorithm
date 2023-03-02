import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 우선순위 큐 사용을 위한 Comparable 인터페이스 구현
    static class Node implements Comparable<Node> {

        int idx;    // 도착 정점
        int cost;   // 도착 정점으로 가는 비용

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node other) {
            // 비용이 작을 수록 높은 우선순위
            return Integer.compare(this.cost, other.cost);
        }
    }

    static final int INFINITE = Integer.MAX_VALUE;  // 무한대 값

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E;    // 정점과 간선의 개수
    static int start;   // 출발 지점

    static ArrayList<Node>[] graph; // 인접 리스트
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] distance;      // 출발 정점부터 도착 정점까지의 최소 거리를 저장하기 위한 배열

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        initGraph();    // 그래프 초기화

        makeGraph();    // 그래프 생성

        dijkstra();     // 다익스트라 알고리즘 실행

        printResult();  // 출발 정점에서 각 정점으로 이동 시의 최소 비용 출력

        br.close();
    }

    // 그래프 초기화
    static void initGraph(){
        graph = new ArrayList[V + 1];   // 정점의 idx는 1 ~ V + 1번까지
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

        distance = new int[V + 1];

        // 최소 거리 정보를 담을 배열 초기화 -> 무한한 값으로
        Arrays.fill(distance, INFINITE);
        distance[start] = 0;    // 출발 지점은 0으로 초기화

        // 시작 정점에서 시작 정점으로 가는 최소 비용은 0
        pq.offer(new Node(start, 0));

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Node minNode = pq.poll();   // 우선순위 큐에서 poll한 노드 -> 현재 최소 비용을 갖는 정점

            // 해당 정점의 비용이 distance 배열에 저장된 값보다 크다면 고려할 필요없음
            // 이 코드가 있어야 중복 방문을 막을 수 있음
            if (distance[minNode.idx] < minNode.cost) {
                continue;
            }

            // 선택된 정점의 모든 인접 정점들을 탐색
            for (Node node : graph[minNode.idx]){

                // 간선으로 연결된 정점들을 모두 우선순위 큐에 넣어준다면 중복 발생
                // 인접 정점으로의 distance 값과 현재 선택된 정점에서 인접 정점으로 가는 비용을 비교
                if (distance[node.idx] > minNode.cost + node.cost){
                    distance[node.idx] = minNode.cost + node.cost;
                    pq.offer(new Node(node.idx, distance[node.idx]));
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