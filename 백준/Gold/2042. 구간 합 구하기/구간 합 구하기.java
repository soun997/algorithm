import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree {

        private long[] arr;
        private long[] tree;
        private int size;

        public SegmentTree(long[] arr){
            build(arr);
        }

        private void build(long[] arr) {
            this.arr = arr;
            this.size = arr.length;
            this.tree = new long[(int)Math.pow(2, Integer.toBinaryString(size).length() + 1)];

            // 살펴볼 배열, 부모 노드, 왼쪽 끝, 오른쪽 끝
            buildRec(1, 0, size - 1);
        }

        private long buildRec(int node, int nodeLeft, int nodeRight) {

            // 리프노드인 경우
            if (nodeLeft == nodeRight){
                return tree[node] = arr[nodeLeft];  // 해당 배열 요소를 트리에 채운 후 반환
            }

            // 리프노드가 아닌 경우 -> left, right 로 나누어서 리프노드 탐색
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;

            long leftVal = buildRec(node * 2, nodeLeft, mid);
            long rightVal = buildRec(node * 2 + 1, mid + 1, nodeRight);

            return tree[node] = merge(leftVal, rightVal);
        }

        // left 와 right 사이의 구간 연산 결과
        public long query(int left, int right){

            return queryRec(left, right, 1, 0,size - 1);
        }

        private long queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {

            // 원하는 구간이 아닌 경우
            if (left > nodeRight || right < nodeLeft){
                return 0;   // default value 리턴
            }

            // 원하는 구간 안에 있는 경우
            if (left <= nodeLeft && right >= nodeRight){
                return tree[node];
            }

            // 원하는 구간과 원하지 않는 구간에 걸쳐져 있는 경우
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            // 더 구간을 잘게 쪼개서 원하는 구간의 연산 결과만을 merge
            return merge(
                    queryRec(left, right, node * 2, nodeLeft, mid),
                    queryRec(left, right, node * 2 + 1, mid + 1, nodeRight)
            );
        }

        public long update(int index, long newValue) {

            return updateRec(index, newValue, 1, 0, size - 1);
        }

        private long updateRec(int index, long newValue, int node, int nodeLeft, int nodeRight) {

            // 업데이트 위치와 관련없는 index
            if (index < nodeLeft || index > nodeRight){
                return tree[node];
            }

            // 업데이트 위치를 찾았을 경우 -> 갱신
            if (nodeLeft == nodeRight){
                return tree[node] = newValue;
            }

            // 파티션을 나눠가면서 Segment Tree 업데이트
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            long leftVal = updateRec(index, newValue, node * 2, nodeLeft, mid);
            long rightVal = updateRec(index, newValue, node * 2 + 1, mid + 1, nodeRight);

            return tree[node] = merge(leftVal, rightVal);

        }

        // 두 요소의 값 연산
        private long merge(long leftVal, long rightVal) {

            return leftVal + rightVal;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] numbers = new long[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        SegmentTree segmentTree = new SegmentTree(numbers);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                segmentTree.update(b, c);
            }
            else {
                int c = Integer.parseInt(st.nextToken());
                sb.append(segmentTree.query(b, c - 1)).append("\n");
            }
        }
        System.out.println(sb);
    }
}