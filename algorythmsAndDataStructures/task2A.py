name, surname = input().split()

alphabet = [chr(i) for i in range(97, 123)]

res = name[0]

i = 1

while i < len(name) and alphabet.index(name[i]) < alphabet.index(surname[0]):
    res += name[i]
    i += 1
res += surname[0]

print(res)