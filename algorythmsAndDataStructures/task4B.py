from collections import deque

t = int(input())

for _ in range(t):
    n, m = map(int, input().split())
    grid = []
    for _ in range(n):
        grid.append(list(map(int, input().split())))

    visited = [[False for _ in range(m)] for _ in range(n)]
    max_depth = 0

    for i in range(n):
        for j in range(m):
            if grid[i][j] > 0 and not visited[i][j]:
                depth = 0
                queue = deque()
                queue.append((i, j))
                visited[i][j] = True

                while queue:
                    x, y = queue.popleft()
                    depth += grid[x][y]

                    for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < n and 0 <= ny < m:
                            if grid[nx][ny] > 0 and not visited[nx][ny]:
                                visited[nx][ny] = True
                                queue.append((nx, ny))
                max_depth = max(max_depth, depth)
    print(max_depth)