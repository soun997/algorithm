class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int pastScore = 0;
        int nowScore = 0;
        for (int i = 0; i < dartResult.length(); i++){            
            if (Character.isDigit(dartResult.charAt(i))){      
                pastScore = nowScore;                
                nowScore = 0; 
                if (dartResult.charAt(i + 1) == '0'){
                    nowScore += 10;
                    i++;
                }
                else
                    nowScore += Character.getNumericValue(dartResult.charAt(i));
                continue;
            }
            else{
                switch(dartResult.charAt(i)){
                    case 'S':
                        break;
                    case 'D':
                        nowScore = (int)Math.pow(nowScore, 2);
                        break;
                    case 'T':
                        nowScore = (int)Math.pow(nowScore, 3);
                        break;
                    // * 혹은 #인 경우
                    default:
                        // 스타상
                        if (dartResult.charAt(i) == '*'){
                            answer -= pastScore;
                            answer += nowScore * 2 + pastScore * 2;
                            nowScore = nowScore * 2;
                        }
                        // 아차상
                        else{
                            nowScore = -nowScore;
                        }
                }   
                // 다음 문자가 숫자라면 answer에 nowScore 계산
                try{
                    if (Character.isDigit(dartResult.charAt(i + 1)) && dartResult.charAt(i) != '*'){
                        answer += nowScore;
                    }
                }
                // 문자열이 끝나서 다음 문자가 없을 경우 예외 발생하므로 잡아줌
                catch(Exception e){
                    if (dartResult.charAt(i) != '*')
                        answer += nowScore;
                }
            }
        }        
        return answer;
    }
}