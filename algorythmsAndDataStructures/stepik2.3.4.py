n, e, d = map(int, input().split())

equals = []
disequals = []

for _ in range(e):
    equals.append(list(map(int, input().split())))
for _ in range(d):
    disequals.append(list(map(int, input().split())))

indexes = [i for i in range (n + 1)]


def eq_root(i):
    while i != indexes[i]:
        i = indexes[i]
    return i

def swap(i, j):
    indexes[i] = j

for i, j in equals:
    new_j = eq_root(j)
    swap(i, new_j)
    swap(j, new_j)

for i, j in disequals:
    if eq_root(i) == eq_root(j):
        print(0)
        exit(0)
print(1)



