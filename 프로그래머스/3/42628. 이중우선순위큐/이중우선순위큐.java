import java.util.*;
import java.util.function.*;

class Solution {
    
    private Map<String, Consumer<Integer>> commands = Map.of(
        "I", this::insert,
        "D", this::delete
    );
    private List<Integer> pq = new ArrayList<>();
    
    public int[] solution(String[] operations) {
        for (String op : operations) {
            String[] chunks = op.split(" ");
            String command = chunks[0];
            int number = Integer.parseInt(chunks[1]);
            commands.get(command).accept(number);
        }
        if (pq.isEmpty()) {
            return new int[]{0, 0};
        }
        return new int[]{pq.get(pq.size() - 1), pq.get(0)};
    }
    
    private void insert(int number) {
        pq.add(number);
        Collections.sort(pq);
    }
    
    private void delete(int option) {
        if (pq.isEmpty()) {
            return;
        }
        if (option == 1) {
            pq.remove(pq.size() - 1);
            return;
        }
        pq.remove(0);
    } 
}