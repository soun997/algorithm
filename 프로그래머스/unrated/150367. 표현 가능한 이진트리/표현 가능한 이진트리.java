import java.util.*;

class Solution {

    String binaryNumber;
    boolean[] binaryTree;
    int idx;
    int size;

    public boolean isBinaryTree(int root){

        if (root >= binaryTree.length) {
            return true;
        }

        if (!isBinaryTree(root * 2)){
            return false;
        }
        if (!isBinaryTree(root * 2 + 1)){
            return false;
        }

        if (binaryTree[root] && !binaryTree[root / 2]){
            return false;
        }

        return true;
    }

    public void fill(int root){
        if (root * 2 >= binaryTree.length){
            if (root < binaryTree.length){
                binaryTree[root] = binaryNumber.charAt(idx++) == '1';
            }
            return;
        }

        fill(root * 2);
        binaryTree[root] = binaryNumber.charAt(idx++) == '1';
        if (root * 2 + 1 < binaryTree.length){
            fill(root * 2 + 1);
        }
    }

    public void fillTree(int root){

        fill(root * 2);

        binaryTree[root] = binaryNumber.charAt(idx++) == '1';

        fill(root * 2 + 1);

    }

    public void makeBinaryTree(){
        binaryTree = new boolean[binaryNumber.length() + 1];
        binaryTree[0] = true;
        size = binaryNumber.length();
        idx = 0;

        fillTree(1);

    }

    public int getMaxNodeCapacity(int len){
        int i = 1;
        while (i - 1 < len){
            i = (i << 1);
        }
        return i - 1;
    }

    public boolean makeBinaryNumber(long number){
        binaryNumber = Long.toBinaryString(number);
        int maxNodeCapacity = getMaxNodeCapacity(binaryNumber.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxNodeCapacity - binaryNumber.length(); i++){
            sb.append("0");
        }
        binaryNumber = sb.append(binaryNumber).toString();
        //System.out.println(binaryNumber);

        makeBinaryTree();

        if (binaryTree[1] && isBinaryTree(1)){
            return true;
        }

        return false;
    }

    public List<Integer> solution(long[] numbers){
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            if (makeBinaryNumber(numbers[i])){
                answer.add(1);
                continue;
            }
            answer.add(0);
        }

        return answer;
    }
}