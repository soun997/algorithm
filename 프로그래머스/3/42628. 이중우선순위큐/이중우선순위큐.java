import java.util.*;

class Solution {
    
    public int[] solution(String[] operations) {
        
        LinkedList<Integer> deque = new LinkedList<>();

        for (String operation : operations) {
            String[] splitted = operation.split(" ");
            
            String command = splitted[0];
            int number = Integer.parseInt(splitted[1]);
            
            switch(command) {
                case "I" -> {
                    deque.add(number);
                    Collections.sort(deque);
                }
                case "D" -> {
                    if (deque.isEmpty()) {
                        continue;
                    }
                    if (number == 1) {
                        deque.remove(deque.size() - 1);
                        continue;
                    }
                    if (number == -1) {
                        deque.remove(0);
                    }
                }
            }
        }
        if (deque.isEmpty()) {
            return new int[]{ 0, 0 };
        }
        return new int[]{ deque.getLast(), deque.getFirst() };
    }
}