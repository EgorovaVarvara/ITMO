def rabin_karp(pattern, text):
    len_pattern = len(pattern)
    len_text = len(text)
    if len_pattern > len_text:
        return []

    p = 10 ** 9 + 7
    x = 263

    x_pow_len = pow(x, len_pattern - 1, p)

    pattern_hash = 0
    window_hash = 0
    for i in range(len_pattern):
        pattern_hash = (pattern_hash * x + ord(pattern[i])) % p
        window_hash = (window_hash * x + ord(text[i])) % p

    indices = []
    if pattern_hash == window_hash and text[:len_pattern] == pattern:
        indices.append(0)

    for i in range(1, len_text - len_pattern + 1):
        window_hash = (window_hash - ord(text[i - 1]) * x_pow_len) % p
        window_hash = (window_hash * x + ord(text[i + len_pattern - 1])) % p

        if pattern_hash == window_hash and text[i:i + len_pattern] == pattern:
            indices.append(i)

    return indices


pattern = input().strip()
text = input().strip()

result = rabin_karp(pattern, text)

print(' '.join(map(str, result)))