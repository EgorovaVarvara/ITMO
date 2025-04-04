n = int(input())

database = {}

for _ in range(n):
    name = input()
    if name not in database:
        print("OK")
        database[name] = 1
    else:
        new_name = name + str(database[name])
        database[name] += 1
        database[new_name] = 1
        print(new_name)
