n = int(input())
tree = [tuple(map(int, input().split())) for _ in range(n)]

in_order_result = []
stack = []
v = 0
while stack or v != -1:
    while v != -1:
        stack.append(v)
        v = tree[v][1]
    v = stack.pop()
    in_order_result.append(tree[v][0])
    v = tree[v][2]

print(' '.join(map(str, in_order_result)))

pre_order_result = []
stack = [0]
while stack:
    v = stack.pop()
    if v == -1:
        continue
    pre_order_result.append(tree[v][0])
    stack.append(tree[v][2])
    stack.append(tree[v][1])  

print(' '.join(map(str, pre_order_result)))

post_order_result = []
stack = [0]
visited = [False] * n
while stack:
    v = stack[-1]
    if v == -1:
        stack.pop()
        continue
    if visited[v]:
        post_order_result.append(tree[v][0])
        stack.pop()
    else:
        visited[v] = True
        stack.append(tree[v][2])
        stack.append(tree[v][1])

print(' '.join(map(str, post_order_result)))
