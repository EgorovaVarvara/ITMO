n, k = map(int, input().split())

numbers = list(map(int, input().split()))

l = 1
r = n + 1
while l + 1 < r:
    m = (l + r + 1) // 2
    num_slice = [0] * n
    for i in range(n):
        num_slice[i] = 1 if numbers[i] >= m else -1
        num_slice[i] += num_slice[i - 1] if i > 0 else 0
    dif = num_slice[k - 1]
    minf = 0
    for i in range(k, n):
        minf = min(minf, num_slice[i - k])
        dif = max(dif, num_slice[i] - minf)

    if dif > 0:
        l = m
    else:
        r = m
print(l)
