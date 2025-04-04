n = int(input())

number_book = {}

for _ in range(n):
    cmd = input().split()
    if cmd[0] == "add":
        number, name = cmd[1:]
        number_book[number] = name
    elif cmd[0] == "del":
        number = cmd[1]
        if number in number_book.keys():
            del number_book[number]
    elif cmd[0] == "find":
        number = cmd[1]
        if number in number_book.keys():
            print(number_book[number])
        else:
            print("not found")
