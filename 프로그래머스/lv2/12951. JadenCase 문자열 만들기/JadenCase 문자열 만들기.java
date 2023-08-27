import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] alphabets = s.toLowerCase().split("");
        boolean flag = true;

        for(String alphabet : alphabets) {
            answer += flag ? alphabet.toUpperCase() : alphabet;
            flag = alphabet.equals(" ") ? true : false;
        }

        return answer;
    }
}