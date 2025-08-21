class Solution {
    public int solution(String skill, String[] skillTrees) {
        int answer = 0;
        for (String skillTree : skillTrees) {
            if (isPossible(skill, skillTree)) {
                answer++;
            }
        }
        return answer;
    }
    
    private boolean isPossible(String skill, String skillTree) {
        int[] indexes = new int[skill.length()];
        for (int i = 0; i < skill.length(); i++) {
            int index = skillTree.indexOf(skill.charAt(i));
            if (index == -1) {
                indexes[i] = Integer.MAX_VALUE;
                continue;
            }
            indexes[i] = index;
        } 
        int prev = -1;
        for (int cur: indexes) {
            if (prev > cur) {
                return false;
            }
            prev = cur;
        }
        return true;
    }
}