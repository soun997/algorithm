import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Trie {

        TrieNode root;

        public Trie() {
            this.root = new TrieNode(' ');
        }

        public void insert(char[] input) {
            TrieNode parent = root;
            for (char ch : input) {
                if (parent.children[ch - 'a'] == null){
                    parent.children[ch - 'a'] = new TrieNode(ch);
                }
                parent = parent.children[ch - 'a'];
            }
        }

        public boolean find(char[] input) {
            TrieNode parent = root;
            for (char ch : input) {
                if (parent.children[ch - 'a'] == null) {
                    return false;
                }
                parent = parent.children[ch - 'a'];
            }
            return true;
        }
    }

    static class TrieNode {

        char ch;
        TrieNode[] children;

        public TrieNode(char ch) {
            this.ch = ch;
            this.children = new TrieNode[26];
        }
    }

    static int N, M;
    static Trie trie;
    static List<String> neverSeen;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trie = new Trie();
        neverSeen = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char[] name = br.readLine().toCharArray();
            trie.insert(name);
        }

        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            neverSeen.add(name);
        }
        Collections.sort(neverSeen);

        StringBuilder answer = new StringBuilder();
        int count = 0;
        for (String name : neverSeen) {
            if (trie.find(name.toCharArray())){
                answer.append(name).append("\n");
                count++;
            }
        }
        System.out.println(count);
        System.out.println(answer);
    }
}