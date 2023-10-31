# Вариант 036
import re
import sys

sys.stdin = open('test_1.txt')


def smile(isu):
    eyes = [':', ';', 'X', ';', '8', '=']
    nose = ['-', '<', '-{', '<{']
    mouth = ['(', ')', 'O', '|', '\\', '/', 'P']
    sml = eyes[isu % 5] + nose[isu % 4] + mouth[isu % 7]
    return (sml)


def ammount(string, patt):
    n = len(re.findall(patt, string))
    return (n)


patt = smile(int(input()))
print('Ваш смайл: ', patt)
for _ in range(5):
    string = input()
    print('Исходная строка: ', string)
    print('Количество смайлов: ', end='')
    print(ammount(string, patt))
    print('')

print('I' + 'T' if '2018'.isdigit() else 'M' + 'O')