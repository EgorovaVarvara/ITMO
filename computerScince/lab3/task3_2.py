import re
xml_file = open('C://Users//Egorova Varvara//Desktop//schedule.xml', 'r')
yaml_file = open('../out.txt', 'w')

flag = 0
counter = 0
for line in xml_file:
    xml_string = line
    yaml_string = ''
    patt = re.findall(r'\s{0,}<\w{1,}>', xml_string)
    if patt:
        name = patt[0]
        name = name.replace('<', '')
        name = name.replace('>', '')
        if name[0] == '\t':
            num = name.count('\t')
            if num != 0:
                name = num * '\t' + '-' + name[num:]
        yaml_string += name + ':'
    patt = re.findall(r'>(.*?)<', xml_string)
    yaml_string += (patt[0] if patt else '')
    yaml_file.write(yaml_string + '\n' if yaml_string else '')



