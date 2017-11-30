from sympy import *

if __name__ == '__main__':
    x = Symbol('x')
    f = sin(x)**3
    integrated = integrate(f, x)
    print(integrated)
