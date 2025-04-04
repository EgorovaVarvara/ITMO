import heapq

def parallel_processing(n, m, tasks):
    heap = [(0, i) for i in range(n)]
    heapq.heapify(heap)

    result = []

    for task in tasks:
        time_free, processor = heapq.heappop(heap)
        start_time = time_free
        time_free += task
        heapq.heappush(heap, (time_free, processor))
        result.append((processor, start_time))

    return result


n, m = map(int, input().split())
tasks = list(map(int, input().split()))

output = parallel_processing(n, m, tasks)

for processor, start_time in output:
    print(processor, start_time)