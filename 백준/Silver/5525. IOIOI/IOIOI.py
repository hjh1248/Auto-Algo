n = int(input())
m = int(input())
s = input()
count = 0
pattern = "I" + "OI" * n
    
i = 0
while i < m - len(pattern) + 1:
    if s[i:i+len(pattern)] == pattern:
        count += 1
        i += 2
    else:
        i += 1
    
print(count)