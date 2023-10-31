# Вариант 11
xml_file = open('C://Users//Egorova Varvara//Desktop//schedule.xml', 'r')
yaml_file = open('../out.txt', 'w')

flag = 0
counter = 0
for line in xml_file:
    xml_string = line
    yaml_string = ''
    for i in range(len(xml_string)):
        symbol = xml_string[i]
        if symbol == '\t':
            yaml_string += '\t'
        elif symbol == ' ':
            yaml_string += ' '
        elif symbol == '<':
            if xml_string[i + 1] != '/':
                if flag != 0:
                    yaml_string += '-'
                else:
                    flag = 1
                    continue
            else:
                break
        elif symbol.isalpha() or symbol.isdigit() or symbol in ':-':
            yaml_string += symbol
        elif symbol == '>':
            yaml_string += ': '
            if i + 1 == len(xml_string) - 1:
                counter += 1
    num = yaml_string.count('\t')
    if num * '\t' != yaml_string:
        yaml_file.write(yaml_string + '\n')

