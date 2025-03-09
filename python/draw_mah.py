# from vector_drawing import *
#
# dino_vectors = [(6,4), (3,1), (1,2), (-1,5), (-2,5), (-3,4), (-4,4),
#     (-5,3), (-5,2), (-2,2), (-5,1), (-4,0), (-2,1), (-1,0), (0,-3),
#     (-1,-4), (1,-4), (2,-3), (1,-2), (3,-1), (5,1)
# ]
#
# draw(
#     Points(*dino_vectors),
#     Polygon(*dino_vectors)
# )
#

from math import sqrt, pi, ceil, floor
import matplotlib
import matplotlib.patches
import matplotlib.pyplot as plt
from matplotlib.collections import PatchCollection
from matplotlib.pyplot import xlim, ylim
import numpy as np
import util.logg as logg

def draw_bar():
    categories = ['A', 'B', 'C', 'D']
    values = [3, 7, 2, 5]
    plt.bar(categories, values)
    plt.title("BasicBar")
    plt.xlabel("Category")
    plt.ylabel("value")
    plt.show()

if __name__ == '__main__':
    logg.log_cyan("draw math start")
    draw_bar()
    logg.log_green("draw math end")
