class DSU:
    def __init__(self, n, sizes):
        self.parent = list(range(n + 1))
        self.size = [0] + sizes
        self.max_size = max(self.size)

    def find(self, x):
        while self.parent[x] != x:
            self.parent[x] = self.parent[self.parent[x]]
            x = self.parent[x]
        return x

    def union(self, x, y):
        xroot = self.find(x)
        yroot = self.find(y)

        if xroot == yroot:
            return

        self.parent[yroot] = xroot
        self.size[xroot] += self.size[yroot]
        self.size[yroot] = 0

        if self.size[xroot] > self.max_size:
            self.max_size = self.size[xroot]



n, m = map(int, input().split())
sizes = list(map(int, input().split()))
queries = []
for i in range(2, 2 + m):
    destination, source = map(int, input().split())
    queries.append((destination, source))
dsu = DSU(n, sizes)
for destination, source in queries:
    dsu.union(destination, source)
    print(dsu.max_size)