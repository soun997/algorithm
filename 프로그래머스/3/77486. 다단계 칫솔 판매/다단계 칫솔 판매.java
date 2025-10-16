import java.util.*;

class Solution {
    static final int PRICE = 100;
    
    Map<String, Integer> nameToIndex = new HashMap<>();
    Node[] linkedList;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        linkedList = new Node[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            nameToIndex.put(enroll[i], i);
            linkedList[i] = new Node(enroll[i], 0);
        }
        for (int i = 0; i < referral.length; i++) {
            if (referral[i].equals("-")) {
                continue;
            }
            linkedList[i].setParent(
                linkedList[nameToIndex.get(referral[i])]);
        }
        // System.out.println(nameToIndex);
        for (int i = 0; i < seller.length; i++) {
            Node cur = linkedList[nameToIndex.get(seller[i])];
            update(cur, amount[i] * PRICE);
        }
        // System.out.println(Arrays.toString(linkedList));
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = linkedList[i].profit;
        }
        return answer;
    }
    
    void update(Node cur, int profit) {
        int referees = profit / 10;
        int mine = profit - referees;
        cur.profit += mine;
        if (cur.parent == null) {
            return;
        }
        update(cur.parent, referees);
    }
    
    static class Node {
        public String name;
        public int profit;
        public Node parent;
        
        public Node(String name, int profit) {
            this.name = name;
            this.profit = profit;
        }
        
        public void setParent(Node node) {
            this.parent = node;
        }
        
        @Override
        public String toString() {
            return "[name: " + name + ", profit: " + profit + ", parent: " + parent + "]";
        }
    }
}