from collections import deque

class SlidingWindowMax:
    def __init__(self, n, arr, m):
        self.n = n
        self.arr = arr
        self.m = m

    def compute_maximums(self):
        if self.m == 1:
            return self.arr

        dq = deque()
        result = []

        for i in range(self.n):
            while dq and dq[0] < i - self.m + 1:
                dq.popleft()

            while dq and self.arr[dq[-1]] < self.arr[i]:
                dq.pop()

            dq.append(i)

            if i >= self.m - 1:
                result.append(self.arr[dq[0]])

        return result

n = int(input().strip())
arr = list(map(int, input().split()))
m = int(input().strip())

window = SlidingWindowMax(n, arr, m)
result = window.compute_maximums()
print(*result)

