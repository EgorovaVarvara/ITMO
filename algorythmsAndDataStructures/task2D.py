def rearrange_students(n, a, b):
    current = b.copy()
    actions = []

    for i in range(n):
        if current[i] == a[i]:
            continue

        target_index = current.index(a[i], i)

        for j in range(target_index, i, -1):
            current[j], current[j - 1] = current[j - 1], current[j]
            actions.append((j, j + 1))

    return actions


n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

actions = rearrange_students(n, a, b)

print(len(actions))
for action in actions:
    print(*action)