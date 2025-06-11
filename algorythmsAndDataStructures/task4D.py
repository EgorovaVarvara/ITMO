from collections import deque

n, d = map(int, input().split())
a = list(map(int, input().split()))
coords = [tuple(map(int, input().split())) for _ in range(n)]

def can_reach(initial_time):
    visited = [dict() for _ in range(n)]
    q = deque()
    q.append((0, initial_time, 0))  

    while q:
        u, time, mask = q.popleft()

        if visited[u].get(mask, -1) >= time:
            continue
        visited[u][mask] = time

        if u == n - 1:
            return True

        for v in range(n):
            if u == v:
                continue
            dist = abs(coords[u][0] - coords[v][0]) + abs(coords[u][1] - coords[v][1])
            cost = d * dist
            if time < cost:
                continue
            new_time = time - cost

            if 0 < v < n - 1:
                bit = 1 << (v - 1)
                if not (mask & bit):
                    q.append((v, new_time + a[v - 1], mask | bit))
            q.append((v, new_time, mask))

    return False


left, right = 0, 10**9
answer = -1

while left <= right:
    mid = (left + right) // 2
    if can_reach(mid):
        answer = mid
        right = mid - 1
    else:
        left = mid + 1

print(answer)
