from sympy import *
from sympy.plotting import plot3d


def first_demo():
    x = Symbol('x')
    f = sin(x) ** 3
    integrated = integrate(f, x)
    print(integrated)


def second_demo():
    t, a, b, Ts = symbols('t a b Ts')
    n, m = symbols('n m', integer = True, positive = True)
    delta_f = 1 / Ts
    f = (a * sin(2 * pi * delta_f * n * t) + b * sin(2 * pi * delta_f * m * t))
    i = 2 * integrate(f * sin(2 * pi * delta_f * m * t), (t, 0.0, Ts)) / Ts
    s = simplify(i)
    print(s)


def third_demo():
    x, y = symbols('x y')
    plot3d(x * exp(-x ** 2 - y ** 2), (x, -3, 3), (y, -2, 2))


def fourth_demo():
    t, m, n = symbols('t m n')
    f = sin(470000000 * t) * sin((470000000 + 1) * t)
    i = integrate(f, (t, 0, pi))
    print(i)


def fifth_demo():
    x = Symbol('x')
    i = integrate(sin(x)**2, x)
    print(i)


def sixth_demo():
    x = Symbol('x')
    dy = diff(x/2 - sin(x)*cos(x)/2, x)
    print(dy)


def ofdm():
    t = symbols('t')
    f1 = 3 * sin(6 * t)
    f2 = 2 * sin(7 * t)
    ff = f1 + f2
    plot(ff)
    mid = ff * sin(7 * t)
    sol = integrate(mid, (t, 0, 2 * pi)) / pi
    print sol


if __name__ == '__main__':
    second_demo()
