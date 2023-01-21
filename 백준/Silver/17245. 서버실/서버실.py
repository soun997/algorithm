N = int(input()) 
nlist = [] 
for _ in range(N):
    _list = list(map(int, input().split())) 
    nlist.extend(_list)

nlist.sort()
nlist.reverse()

# 컴퓨터가 총 몇 대 있는지
ntotal = sum(nlist)
mtotal = 0
_min = 1
while mtotal < ntotal / 2:
    if nlist[-1] // _min == 0:
        nlist.pop()
        continue
    _min += 1
    mtotal += len(nlist)
    
print(_min - 1)