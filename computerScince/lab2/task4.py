def Heming(begin):
    syndrome1 = str(int(begin[0]) ^ int(begin[2]) ^ int(begin[4]) ^ int(begin[6]))
    syndrome2 = str(int(begin[1]) ^ int(begin[2]) ^ int(begin[5]) ^ int(begin[6]))
    syndrome3 = str(int(begin[3]) ^ int(begin[4]) ^ int(begin[5]) ^ int(begin[6]))
    main_syndrome = syndrome1 + syndrome2 + syndrome3
    errors = {'000': -1, '001': 3, '010': 1, '011': 5, '100': 0, '101': 4, '110': 2, '111': 6}
    error = errors[main_syndrome]
    names = {0: 'r1', 1: 'r2', 2: 'i1', 3: 'r3', 4: 'i2', 5: 'i3', 6: 'i4'}
    if error < 0:
        print('Сообщение "' + begin[2] + begin[4] + begin[5] + begin[6] + '" верное')
    else:
        end = begin[:error] + str(int(not(int(begin[error])))) + begin[error + 1:]
        print('Верное сообщение: "' + end[2] + end[4] + end[5] + end[6] + '"')
        print('Ошибка в символе ' + str(error + 1) + '(' + names[error] + ')')


Heming(input('Введите полученное сообщение: '))
