
from decimal import *

LOOP_COUNT = 128


def show_step_result(n, l_inner, m, l_outer, r, step):
    pi_low = "%s" % (l_inner / (Decimal(2) * r))
    pi_hig = "%s" % (l_outer / (Decimal(2) * r))

    pi = ""
    min_len = min(len(pi_low), len(pi_hig))
    for i in range(0, min_len):
        if pi_low[i] != pi_hig[i]:
            break
        pi += pi_low[i]

    if pi == "":
        pi = "between %s and %s" % (pi_low, pi_hig)

    print "Calculation NO.%d, n = %e, m = %e, Pie is approx. %s"\
          % (step, n, m, pi)


def f(n, l, r):
    return Decimal(2) * n * \
           (Decimal(2) * r * r - Decimal(2) * r * (r * r - (l * l)
           / (Decimal(4) * n * n)).sqrt()).sqrt()


def g(m, l, r):
    return (Decimal(2) * r * l) / (r + (r * r + (l * l)
           / (Decimal(4) * m * m)).sqrt())


def main():
    r = Decimal(1)
    n = Decimal(6)
    l_inner = Decimal(6)
    m = Decimal(4)
    l_outer = Decimal(8)

    for i in range(0, LOOP_COUNT):
        if i == 0:
            show_step_result(n, l_inner, m, l_outer, r, i)
        l_inner = f(n, l_inner, r)
        n *= Decimal(2)
        l_outer = g(m, l_outer, r)
        m *= Decimal(2)
        show_step_result(n, l_inner, m, l_outer, r, i + 1)


if __name__ == '__main__':
    getcontext().prec = 256
    main()
