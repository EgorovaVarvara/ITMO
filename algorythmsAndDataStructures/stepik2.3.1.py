class MinHeap:
    def __init__(self, arr):
        self.arr = arr
        self.n = len(arr)
        self.swaps = []

    def heapify(self, i):
        left = 2 * i + 1
        right = 2 * i + 2
        smallest = i

        if left < self.n and self.arr[left] < self.arr[smallest]:
            smallest = left
        if right < self.n and self.arr[right] < self.arr[smallest]:
            smallest = right

        if smallest != i:
            self.arr[i], self.arr[smallest] = self.arr[smallest], self.arr[i]
            self.swaps.append((i, smallest))
            self.heapify(smallest)

    def build_heap(self):
        for i in range(self.n // 2 - 1, -1, -1):
            self.heapify(i)

    def get_result(self):
        return self.swaps

n = int(input().strip())
arr = list(map(int, input().split()))

heap = MinHeap(arr)
heap.build_heap()

swaps = heap.get_result()
print(len(swaps))
for i, j in swaps:
    print(i, j)
