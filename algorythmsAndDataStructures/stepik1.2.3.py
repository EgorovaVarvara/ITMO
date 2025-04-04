from collections import deque


def process_packets(size, n, packets):
    queue = deque()
    result = []
    current_time = 0

    for i in range(n):
        arrival, duration = packets[i]

        while queue and queue[0] <= arrival:
            queue.popleft()

        if len(queue) < size:
            if queue:
                start_time = max(queue[-1], arrival)
            else:
                start_time = arrival
            queue.append(start_time + duration)
            result.append(start_time)
        else:
            result.append(-1)

    return result


size, n = map(int, input().split())
packets = [tuple(map(int, input().split())) for _ in range(n)]

output = process_packets(size, n, packets)

for time in output:
    print(time)