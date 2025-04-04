import sys
from bisect import bisect_left, bisect_right


def minimal_affected_elements(n, I, a):
    max_K = 2 ** (8 * I // n)
    unique_values = sorted(list(set(a)))
    if max_K >= len(unique_values):
        return 0

    a_sorted = sorted(a)
    min_affected = float('inf')

    left = 0
    right = len(unique_values) - 1

    while left <= right:
        mid = (left + right) // 2
        K = mid + 1

        current_min = float('inf')
        for i in range(len(unique_values) - K + 1):
            l = unique_values[i]
            r = unique_values[i + K - 1]

            affected = bisect_left(a_sorted, l) + (n - bisect_right(a_sorted, r))
            if affected < current_min:
                current_min = affected

        if current_min < min_affected:
            min_affected = current_min

        if K < max_K:
            left = mid + 1
        else:
            right = mid - 1

    return min_affected


n, I = map(int, input().split())
a = list(map(int, input().split()))

print(minimal_affected_elements(n, I, a))
