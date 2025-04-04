def evaluate_expression(terms, target):
    expression = "".join(terms)
    return eval(expression) == target


def generate_permutations(elements, length):
    if length == 0:
        yield []
    else:
        for i in range(len(elements)):
            for perm in generate_permutations(elements[:i] + elements[i + 1:], length - 1):
                yield [elements[i]] + perm


def solve_rebus(rebus):
    parts = rebus.split()
    target = int(parts[-1])
    expression_parts = parts[:-2]  # убираем '=', оставляем только выражение

    if len(expression_parts) == 1 and expression_parts[0] == '?':
        if 1 <= target <= 1000000:
            print("Possible")
            print(f"{target} = {target}")
        else:
            print("Impossible")
        return

    question_indices = [i for i, part in enumerate(expression_parts) if part == '?']
    num_questions = len(question_indices)

    possible_numbers = list(range(1, target + 1))

    for perm in generate_permutations(possible_numbers, num_questions):
        temp_expr = expression_parts[:]
        for index, value in zip(question_indices, perm):
            temp_expr[index] = str(value)

        if evaluate_expression(temp_expr, target):
            print("Possible")
            print(" ".join(temp_expr) + f" = {target}")
            return

    print("Impossible")


# Пример использования
rebus = input()
solve_rebus(rebus)
