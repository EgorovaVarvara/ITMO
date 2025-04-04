t = int(input())

for _ in range(t):
    input()
    num_segments, num_points = map(int, input().split())
    points = []
    for i in range(num_points):
        coord, weight = map(int, input().split())
        points.append([weight, coord, i + 1])
    points.sort()

    total_cost = 0
    selected_points = []
    for i in range(2 * num_segments):
        weight, coord, index = points[i]
        selected_points.append([coord, index])
        total_cost += weight
    print(total_cost)
    selected_points.sort()
    for i in range(num_segments):
        left, right = selected_points[i][1], selected_points[2 * num_segments - i - 1][1]
        print(f"{left} {right}")
    print()

