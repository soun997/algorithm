import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    static int test;
    static int m, a;
    static int[][] command;
    static BC[] bc;
    static int[] A, B;
    static int maxChargeAmount;
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            command = new int[2][m + 1];
            bc = new BC[a];

            A = new int[]{1, 1};
            B = new int[]{10, 10};

            maxChargeAmount = 0;

            // 사용자 이동 명령 input
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < m + 1; j++) {
                    command[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // BC 정보 input
            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bc[i] = new BC(x, y, c, p);
            }

            // 사용자를 이동시킨다
            for (int i = 0; i < m + 1; i++){
                move(command[0][i], command[1][i]);
                maxChargeAmount += getChargeAmount();
            }

            sb.append("#").append(t + 1).append(" ").append(maxChargeAmount).append("\n");
        }
        System.out.println(sb);
    }

    static void move(int commandA, int commandB){

        // 좌표 변경
        A[0] = A[0] + dx[commandA];
        A[1] = A[1] + dy[commandA];

        B[0] = B[0] + dx[commandB];
        B[1] = B[1] + dy[commandB];

        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        int choiceA = -1;
        int choiceB = -1;
        for (int i = 0; i < a; i++){
            int diffA = Math.abs(A[0] - bc[i].x) + Math.abs(A[1] - bc[i].y);
            if (diffA <= bc[i].coverage){
                setA.add(i);
                choiceA = i;
            }

            int diffB = Math.abs(B[0] - bc[i].x) + Math.abs(B[1] - bc[i].y);
            if (diffB <= bc[i].coverage){
                setB.add(i);
                choiceB = i;
            }
        }
        ///System.out.println(choiceA + " " + choiceB);

        if (choiceA != -1 && choiceB != -1){
            bc[choiceA].users++;
            bc[choiceB].users++;
            int max = bc[choiceA].performance / bc[choiceA].users
                    + bc[choiceB].performance / bc[choiceB].users;
            bc[choiceA].users--;
            bc[choiceB].users--;
            for (int idA : setA){
                for (int idB : setB){
                    bc[idA].users++;
                    bc[idB].users++;
                    int consider = bc[idA].performance / bc[idA].users
                            + bc[idB].performance / bc[idB].users;
                    bc[idA].users--;
                    bc[idB].users--;
                    //System.out.println(consider);
                    if (max < consider){
                        choiceA = idA;
                        choiceB = idB;
                        max = consider;
                    }
                }
            }
            bc[choiceA].users++;
            bc[choiceB].users++;
            return;
        }

        if (choiceA != -1){
            int max = bc[choiceA].performance / (bc[choiceA].users + 1);
            for (int idA : setA){
                int consider = bc[idA].performance / (bc[idA].users + 1);
                if (max < consider){
                    choiceA = idA;
                    max = consider;
                }
            }
            bc[choiceA].users++;
        }
        if (choiceB != -1){
            int max = bc[choiceB].performance / (bc[choiceB].users + 1);
            for (int idB : setB){
                int consider = bc[idB].performance / (bc[idB].users + 1);
                if (max < consider){
                    choiceB = idB;
                    max = consider;
                }
            }
            bc[choiceB].users++;
        }

    }


    static int getChargeAmount(){
        int charge = 0;
        for (int i = 0; i < a; i++){
            //System.out.println(i + " " + bc[i].users);
            if (bc[i].users == 0)
                continue;
            charge += bc[i].performance;
            bc[i].users = 0;
        }
        //System.out.println(charge);
        return charge;
    }

    static class BC {

        int x;
        int y;
        int coverage;
        int performance;
        int users;

        public BC(int x, int y, int coverage, int performance){
            this.x = x;
            this.y = y;
            this.coverage = coverage;
            this.performance = performance;
            this.users = 0;
        }
    }
}