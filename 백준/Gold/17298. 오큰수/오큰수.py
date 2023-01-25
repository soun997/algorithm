# 백준 17298 오큰수 정답
# 원리 : 뒤에서부터 읽어들이기, 스택을 이용하여 오큰수 거름, 속도 향상
from collections import deque

N = int(input())
A = [*map(int, input().split())]
NGE = [None] * N
stk = deque()
# 초기 세팅
NGE[N-1] = -1   # An(수열 마지막 수) 오큰수는 무조건 -1
stk.append(A[N-1])

for i in range(N-2, -1, -1):
    if A[i] < stk[-1]:
        NGE[i] = stk[-1]
        stk.append(A[i])
        continue
    # 이중 반복문이긴 하지만 간단한 pop연산뿐
    # 스택의 크기와 while문 실행 횟수는 반비례
    # 최악의 상황에도 시간 복잡도는 O(n^2) 이하임
    while stk and A[i] >= stk[-1]:
        stk.pop()
    if not stk:
            NGE[i] = -1
            stk.append(A[i])
    else:
        NGE[i] = stk[-1]
        stk.append(A[i])

print(*NGE)
    