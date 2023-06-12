import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int N;
    static Map<Integer, Node> tree;
    static int total;
    static int duplicated;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();
        total = 0;
        duplicated = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.computeIfAbsent(p, key -> new Node(a, b));
        }

        circuit(1, 0);

        System.out.println(total - duplicated);
    }

    static boolean circuit(int root, int depth){
        // 하위 노드가 없다면 return
        if (root == -1) {
            return false;
        }

        total++;

        if (!circuit(tree.get(root).left, depth + 1)){
            duplicated = depth;
            total--;
        }
        else {
            total++;
        }

        total++;

        if (!circuit(tree.get(root).right, depth + 1)){
            duplicated = depth;
            total--;
        }
        else {
            total++;
        }

        return true;
    }
}