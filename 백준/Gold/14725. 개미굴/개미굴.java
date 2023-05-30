import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static class Trie {

        static class TrieNode {

            String word;
            int depth;
            TreeMap<Character, TrieNode> children;
            boolean isWord;

            public TrieNode(String word, int depth){
                this.word = word;
                this.depth = depth;
                this.children = new TreeMap<>();
                this.isWord = false;
            }
        }

        TrieNode root;
        static int depth;

        public Trie(){
            this.root = new TrieNode("", 0);
        }

        public void insert(String[] words){
            TrieNode node = this.root;
            depth = 0;
            for (String word : words){
                StringBuilder subWord = new StringBuilder();
                for (Character c : word.toCharArray()){

                    subWord.append(c);
                    // computeIfAbsent(): Map에 key에 대응하는 value가 존재할 때: 해당 value 반환, 존재하지 않을 때: 해당 함수 실행
                    // 자식 노드 존재 -> 자식노드로 이동, 자식 노드 없다면 새로 Node 만들어서 넣어줌
                    node = node.children.computeIfAbsent(c, child -> new TrieNode(subWord.toString(), depth));
                }
                node.isWord = true; // 해당 단어의 마지막 문자에는 true 체크
                depth++;
            }
        }

        public boolean print(TrieNode root, int depth, boolean isDone){
            TrieNode node = root;
            for (Character c : node.children.keySet()){
                if (isDone){
                    sb.append("--".repeat(node.children.get(c).depth));
                }

                if (node.children.get(c).isWord){
                    sb.append(node.children.get(c).word);
                    sb.append("\n");
                    isDone = print(node.children.get(c), depth + 1, true);
                    continue;
                }
                isDone = print(node.children.get(c), depth, false);
            }

            return isDone;
        }
    }

    static int N;
    static Trie trie;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        trie = new Trie();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] inputs = new String[K];
            for (int j = 0; j < K; j++) {
                inputs[j] = st.nextToken();
            }
            trie.insert(inputs);
        }

        trie.print(trie.root, 0, true);
        System.out.println(sb);
    }
}