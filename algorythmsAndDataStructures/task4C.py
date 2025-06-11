with open("input.txt", "r") as f:
    N, M = map(int, f.readline().split())
    K = int(f.readline())
    data = list(map(int, f.readline().split()))
    fires = [(data[i], data[i+1]) for i in range(0, 2*K, 2)]

max_dist = -1
res_x = -1
res_y = -1


for i in range(1, N + 1):
    for j in range(1, M + 1):
        min_dist = float('inf')
        for fx, fy in fires:
            dist = abs(fx - i) + abs(fy - j)
            if dist < min_dist:
                min_dist = dist
        if min_dist > max_dist:
            max_dist = min_dist
            res_x, res_y = i, j

with open("output.txt", "w") as f:
    f.write(f"{res_x} {res_y}\n")
