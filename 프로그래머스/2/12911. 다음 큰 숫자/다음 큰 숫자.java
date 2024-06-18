class Solution {
    
    public int solution(int n) {
        int m = n + 1;
        while (m <= 1_000_000) {
            if (isNBN(n, m)) {
                return m;
            }
            m++;
        }
        return n;
    }
    
    private boolean isNBN(int n, int m) {
        int nBitCount = Integer.bitCount(n);
        int mBitCount = Integer.bitCount(m);
        
        return nBitCount == mBitCount;
    }
}