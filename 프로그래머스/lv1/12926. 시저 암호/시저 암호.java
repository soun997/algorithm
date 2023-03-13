class Solution {
    public String solution(String s, int n) {
        String answer;
        char[] charStr = s.toCharArray();
        for (int i = 0; i < charStr.length; i++){
            if (charStr[i] == ' ')
                continue;
            if (Character.isUpperCase(charStr[i])){
                if (charStr[i] + n > 'Z')
                    charStr[i] += n - 26;
                else
                    charStr[i] += n;
            }
            if (Character.isLowerCase(charStr[i])){
                if (charStr[i] + n > 'z')
                    charStr[i] += n - 26;
                else
                    charStr[i] += n;
            }                    
        }
        answer = new String(charStr);
        return answer;
    }
}