import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static class Word implements Comparable<Word>{

        int size;
        char[] characters;

        public Word(int size, char[] characters){
            this.size = size;
            this.characters = characters;
        }

        @Override
        public int compareTo(Word other) {
            if (this.size < other.size){

                return -1;
            } else if (this.size == other.size){

                for (int i = 0; i < this.size; i++){
                    if (this.characters[i] == other.characters[i]){
                        continue;
                    }

                    return Character.compare(this.characters[i], other.characters[i]);
                }
                return 0;

            } else {

                return 1;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(characters);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Word> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            pq.offer(new Word(input.length, input));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            String result = pq.poll().toString();
            if (!pq.isEmpty()){
                String compare = pq.peek().toString();
                if (result.equals(compare)){
                    continue;
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}