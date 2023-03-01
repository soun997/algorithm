import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static class Node {

        int id;
        int count;
        boolean isVisited;
        List<Node> children;

        public Node(int id){
            this.id = id;
            this.count = 0;
            this.isVisited = false;
            this.children = new ArrayList<>();
        }

        public void addChild(Node child){
            children.add(child);
        }
    }

    static int N;
    static int start;
    static Map<Integer, Node> nodes;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            nodes = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if (!nodes.containsKey(from)) {
                    nodes.put(from, new Node(from));
                }
                if (!nodes.containsKey(to)){
                    nodes.put(to, new Node(to));
                }
                nodes.get(from).addChild(nodes.get(to));
            }

            sb.append("#").append(t + 1).append(" ").append(bfs(start)).append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(int id){

        Queue<Node> q = new ArrayDeque<>();
        q.offer(nodes.get(id));

        Node maxNode = nodes.get(id);
        while (!q.isEmpty()){
            Node parent = q.poll();

            for (Node node : parent.children){
                if (node.isVisited){
                    continue;
                }
                q.offer(node);
                node.isVisited = true;
                node.count = parent.count + 1;

                if (node.count > maxNode.count){
                    maxNode = node;
                }
                if (node.count == maxNode.count){
                    if (node.id > maxNode.id){
                        maxNode = node;
                    }
                }
            }
        }

        return maxNode.id;
    }

}