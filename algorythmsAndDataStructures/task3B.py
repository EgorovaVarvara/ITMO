n = int(input())

summ = {}

players = []
scores = []

for i in range(n):
    inpt = input().split()
    players.append(inpt[0])
    scores.append(int(inpt[1]))
    if players[i] in summ:
        scores[i] += summ[players[i]]
        summ[players[i]] = scores[i]
    else:
        summ[players[i]] = scores[i]
result = max(summ.values())

for i in range(n):
    if result == summ[players[i]] and scores[i] >= result:
        print(players[i])
        break
