s = input()

bracket_pairs = {">":"<", "}":"{", ")":"(", "]":"["}
stack = []
replacements = 0

for char in s:
    if char in "<{([":
        stack.append(char)
    else:
        if not stack:
            print("Impossible")
            exit()
        top = stack.pop()
        if top != bracket_pairs[char]:
            replacements += 1

print(replacements if not stack else "Impossible")
