n = int(input())
all_numbers = []
for i in range(n):
    all_numbers.append(i + 1)

summ = n + (n - 1)

if str(summ).replace("9", "") == "":
    print(1)
elif summ < 9:
    print(n * (n - 1) // 2)
else:
    nine_list = []
    result = 0
    length = len(str(summ)) - 1
    nine_string = "9" * length
    for index in range(9):
        nine_list.append(int(str(index) + nine_string))
    for index in range(len(nine_list)):
        if nine_list[index] <= n + 1:
            result += nine_list[index] // 2
        elif nine_list[index] > 2 * n - 1:
            continue
        else:
            result += (n - (nine_list[index] - n) + 1) // 2
    print(result)
