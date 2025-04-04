import sys

sys.setrecursionlimit(20000)

from collections import deque


class TreeHeight:
    def __init__(self, n, parents):
        self.n = n
        self.parents = parents
        self.tree = [[] for _ in range(n)]
        self.root = -1

        for child, parent in enumerate(parents):
            if parent == -1:
                self.root = child
            else:
                self.tree[parent].append(child)

    def compute_height(self):
        if self.root == -1:
            return 0

        queue = deque([(self.root, 1)])  # (вершина, уровень)
        max_height = 0

        while queue:
            node, height = queue.popleft()
            max_height = max(max_height, height)
            queue.extend((child, height + 1) for child in self.tree[node])

        return max_height


n = int(input())
parents = list(map(int, input().split()))

tree = TreeHeight(n, parents)
print(tree.compute_height())