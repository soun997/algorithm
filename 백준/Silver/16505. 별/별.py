def print_star(base : int):
    for i in range(base):
        print(stars[i].rstrip())

def append_star(stars, base : int, s_base : int, blank : int):
    temp_stars = stars[:]
    for i in range(0, s_base // 2):
        stars[i] += temp_stars[i]
    for j in range(0, s_base // 2):
        stars[s_base // 2 + j] = temp_stars[j]
        stars[s_base // 2 + j] += ' ' * blank
    s_base *= 2
    blank = blank * 2
    if blank == 0: blank += 1
    if s_base > base:   return
    append_star(stars, base, s_base, blank)

inpt = int(input())
base = 2 ** inpt
s_base = 1
blank = 0
stars = [''] * (2 ** inpt)
stars[0] = '*'
append_star(stars, base, s_base, blank)
print_star(base)