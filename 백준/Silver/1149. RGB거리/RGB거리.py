import sys
sys.setrecursionlimit(10**7)
def cost(n, memo=[0, 0, 0]):
    if n == 0:
        return print(min(memo))
    tmp = []
    array = input().split()
    tmp.append(min((int(array[0]) + memo[1]), (int(array[0]) + memo[2])))
    tmp.append(min((int(array[1]) + memo[0]), (int(array[1]) + memo[2])))
    tmp.append(min((int(array[2]) + memo[0]), (int(array[2]) + memo[1])))
    memo = []
    memo += tmp
    return cost(n-1, memo)
cost(int(input()))