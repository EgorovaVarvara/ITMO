def check_brackets(s):
    stack = []
    bracket_map = {')': '(', ']': '[', '}': '{'}
    position_map = []

    for i, char in enumerate(s, 1):
        if char in "({[":
            stack.append(char)
            position_map.append(i)
        elif char in ")}]":
            if not stack or stack[-1] != bracket_map[char]:
                return i
            stack.pop()
            position_map.pop()

    return "Success" if not stack else position_map[0]


s = input().strip()
print(check_brackets(s))
