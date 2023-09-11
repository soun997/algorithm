import java.util.*;
 
class Solution {
    HashMap<String, Integer> dict = new HashMap<>();
    public int[] solution(String msg) {
        int[] answer;
        ArrayList<Integer> ans = new ArrayList<>();        
        
        int dictIdx = 1;
        for(int i='A'; i<='Z'; i++){
            dict.put( String.valueOf((char)i), dictIdx++) ;
        }
       
        int idx = 0;
        while(idx < msg.length()){
            String w = "";
            while(idx < msg.length()){
                if(!dict.containsKey(w+msg.charAt(idx))){
                    break;
                }
                w += msg.charAt(idx++);
            }
            
            ans.add(dict.get(w));
            if(idx < msg.length()){
                dict.put(w+msg.charAt(idx), dictIdx++);
            }            
        }
        
        answer = new int[ans.size()];
        for(int i =0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
           
        return answer;
    }
}