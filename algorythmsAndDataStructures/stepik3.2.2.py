class HashTable:
    def __init__(self, size):
        self.size = size
        self.table = [[] for _ in range(size)]
        self.p = 1000000007
        self.x = 263

    def hash(self, string):
        h = 0
        for i, char in enumerate(string):
            h = (h + ord(char) * pow(self.x, i, self.p)) % self.p
        return h % self.size

    def add(self, string):
        index = self.hash(string)
        if string not in self.table[index]:
            self.table[index].insert(0, string)

    def check(self, index):
        return ' '.join(self.table[index])

    def find(self, string):
        index = self.hash(string)
        return 'yes' if string in self.table[index] else 'no'

    def delete(self, string):
        index = self.hash(string)
        if string in self.table[index]:
            self.table[index].remove(string)


m = int(input())
n = int(input())

ht = HashTable(m)

for _ in range(n):
    parts = input().split()
    if parts[0] == 'add':
        ht.add(parts[1])
    elif parts[0] == 'find':
        print(ht.find(parts[1]))
    elif parts[0] == 'del':
        ht.delete(parts[1])
    elif parts[0] == 'check':
        print(ht.check(int(parts[1])))