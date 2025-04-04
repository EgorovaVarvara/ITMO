import heapq

def f(x):
    return len(str(x))

t = int(input())
for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))

    max_heap_a = []
    max_heap_b = []
    count_a = {}
    count_b = {}

    for num in a:
        heapq.heappush(max_heap_a, -num)
        count_a[num] = count_a.get(num, 0) + 1
    for num in b:
        heapq.heappush(max_heap_b, -num)
        count_b[num] = count_b.get(num, 0) + 1

    operations = 0

    common_keys = set(count_a.keys()) & set(count_b.keys())
    for key in common_keys:
        common_count = min(count_a[key], count_b[key])
        count_a[key] -= common_count
        count_b[key] -= common_count

    max_heap_a = []
    max_heap_b = []
    for num in count_a:
        for _ in range(count_a[num]):
            heapq.heappush(max_heap_a, -num)
    for num in count_b:
        for _ in range(count_b[num]):
            heapq.heappush(max_heap_b, -num)

    while max_heap_a and max_heap_b:
        current_a = -heapq.heappop(max_heap_a)
        current_b = -heapq.heappop(max_heap_b)

        if current_a == current_b:
            continue
        elif current_a > current_b:
            new_a = f(current_a)
            operations += 1
            heapq.heappush(max_heap_a, -new_a)
            heapq.heappush(max_heap_b, -current_b)
        else:
            new_b = f(current_b)
            operations += 1
            heapq.heappush(max_heap_b, -new_b)
            heapq.heappush(max_heap_a, -current_a)

    print(operations)