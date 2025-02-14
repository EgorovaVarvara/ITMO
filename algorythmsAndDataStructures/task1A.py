t = int(input())
data = []
for i in range(t):
    n = int(input())
    s = input()
    data.append([n, s])

for i in data:
    string = i[1]
    opened = 0
    result = 0
    for j in range(i[0]):
        if string[j] == ')' and opened > 0:
            opened -= 1
            result -= 1
        elif string[j] == '(':
            opened += 1
            result += 1
    print(result)