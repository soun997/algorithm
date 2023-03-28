class Solution {

    int[][] key;
    int[][] lock;
    int point;

    boolean check(int[][] newLock, int point, int len) {
        // TODO: 자물쇠 영역이 모두 유효한지 확인
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(newLock[point + i][point + j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    void match(int[][] newLock, int[][] key, int rot, int x, int y) {
        // TODO: newLock 배열에 key 배열을 더해줌
        int len = key.length;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {

                // 한 번도 회전하지 않았을 때
                if(rot == 0) {
                    newLock[x + i][y + j] += key[i][j];
                }
                // 시계방향으로 90도 회전
                else if(rot == 1) {
                    newLock[x + i][y + j] += key[j][len - i - 1];
                }
                // 180도 회전
                else if(rot == 2) {
                    newLock[x + i][y + j] += key[len - i - 1][len- j - 1];
                }
                // 270도 회전
                else {
                    newLock[x + i][y + j] += key[len - j - 1][i];
                }
            }
        }
    }

    int[][] initNewLock(){
        // TODO: newLock을 생성하여 반환
        int[][] newLock = new int[lock.length + key.length * 2][lock.length + key.length * 2];
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                newLock[i + point][j + point] = lock[i][j];
            }
        }
        return newLock;
    }

    boolean rotateKey(int x, int y){
        //key 회전
        for(int r = 0; r < 4; r++) {
            // lock 배열을 확장한 newLock
            // -> 키의 일부가 자물쇠의 영역을 벗어나도 일치하는 경우 고려
            int[][] newLock = initNewLock();

            match(newLock, key, r, x, y);

            // 자물쇠 영역이 모두 유효한지 확인
            if(check(newLock, point, lock.length)) {
                return true;
            }
        }
        return false;
    }

    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        this.point = key.length - 1;

        // 이동거리: 열쇠와 자물쇠가 처음으로 겹치는 부분 + 자물쇠의 크기
        for(int x = 0; x < point + lock.length; x++) {
            for(int y = 0; y < point + lock.length; y++) {
                if (rotateKey(x, y)){
                    return true;
                }
            }
        }
        return false;
    }
}