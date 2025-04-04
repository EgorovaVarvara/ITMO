class MaxStack:
    def __init__(self):
        self.stack = []
        self.max_stack = []

    def push(self, value):
        self.stack.append(value)
        if not self.max_stack or value >= self.max_stack[-1]:
            self.max_stack.append(value)

    def pop(self):
        if not self.stack:
            return None
        value = self.stack.pop()
        if value == self.max_stack[-1]:
            self.max_stack.pop()
        return value

    def max(self):
        if not self.max_stack:
            return None
        return self.max_stack[-1]

def process_queries(queries):
    stack = MaxStack()
    output = []
    for query in queries:
        if query.startswith('push'):
            value = int(query.split()[1])
            stack.push(value)
        elif query == 'pop':
            stack.pop()
        elif query == 'max':
            max_value = stack.max()
            if max_value is not None:
                output.append(max_value)
    return output

q = int(input())
queries = [input().strip() for _ in range(q)]

output = process_queries(queries)

for value in output:
    print(value)