import heapq

# Чтение входных данных
n, m = map(int, input().split())

graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b, w = map(int, input().split())
    graph[a].append((b, w))
    graph[b].append((a, w))

INF = float('inf')
dist = [INF] * (n + 1)
dist[1] = 0
prev = [-1] * (n + 1)
heap = []
heapq.heappush(heap, (0, 1))

while heap:
    current_dist, u = heapq.heappop(heap)
    if current_dist > dist[u]:
        continue
    for v, w in graph[u]:
        if dist[v] > dist[u] + w:
            dist[v] = dist[u] + w
            prev[v] = u
            heapq.heappush(heap, (dist[v], v))

if dist[n] == INF:
    print(-1)
else:
    path = []
    current = n
    while current != -1:
        path.append(current)
        current = prev[current]
    path.reverse()
    print(' '.join(map(str, path)))