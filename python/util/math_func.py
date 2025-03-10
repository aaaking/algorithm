from math import sqrt, pi, ceil, floor, fabs
import math
from sympy import symbols, Eq, solve
import numpy as np

def math_func():
    x = symbols('x')
    equation = Eq(x ** 2 + 9 * x ** 2 / (x - 3) ** 2, 16)
    solutions = solve(equation, x)
    print("The solutions are:", solutions)



math_func()