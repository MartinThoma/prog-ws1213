def tribonacci(n):
    if n < 3:
        return n
    else:
        return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3)

def tribonacciBottomUp(n):
    last = 1
    secondLast = 1
    thirdLast = 1
    for i in range(2,n):
        new = last + secondLast + thirdLast
        thirdLast = secondLast
        secondLast = last
        last = new
    return last

def fillIt(n):
    solutions

for i in xrange(0,40+1):
    print("<tr><td>%i</td><td>%i</td></tr>" % (i, tribonacciBottomUp(i)))
