class Solution {
    public int solution(int[] citations) {
        int[] counts = new int[10001];
        int max = Integer.MIN_VALUE;
        for (int citation : citations) {
            counts[citation]++;
            max = Math.max(max, citation);
        }
        int hIndex = 0;
        for (int h = max; h >= 0; h--) {
            if (hIndex >= h) {
                break;
            }
            hIndex += counts[h];
        }
        
        return hIndex;
    }
}