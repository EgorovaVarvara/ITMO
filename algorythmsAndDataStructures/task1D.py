import math

def main():
    nums_count = int(input())
    inp = list(map(int, input().split()))
    log_sum = 0
    for i in inp:
        log_sum += math.log(i)
    avg_geom = math.exp(log_sum / len(inp))
    round_avg_geom = round(avg_geom)
    if (abs(avg_geom - round_avg_geom) <= 0.00001 and all(int(el) == 1 or math.gcd(int(el), round_avg_geom) != 1 for el in inp)) != True:
        print("NO")
        return 0
    print("YES")

if __name__ == '__main__':
    tests_count = int(input())
    for i in range (tests_count):
        main()