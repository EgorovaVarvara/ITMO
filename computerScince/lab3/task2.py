# Задание 2.5

import re
import sys

sys.stdin = open('test_2.txt')


def time(text):
    patt = "[0-2][0-3]:[0-5][0-9](:[0-5][0-9])?|[0-1][0-9]:[0-5][0-9](:[0-5][0-9])?"
    text = re.sub(patt, '(TDB)', text)
    return (text)

# \d{2,5}
for _ in range(5):
    text = input()
    print('Исходный текст: ', text)
    print('Полученный текст: ', end = '')
    print(time(text))
    print('')
