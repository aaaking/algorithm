from math import sqrt, pi, ceil, floor
import matplotlib
import matplotlib.patches
from matplotlib.collections import PatchCollection
import matplotlib.pyplot as plt
from matplotlib.pyplot import xlim, ylim
import numpy as np

blue = 'C0'
black = 'k'
red = 'C3'
green = 'C2'
purple = 'C4'
orange = 'C2'
gray = 'gray'

class Polygon():
    def __init__(self, *vertices, color=blue, fill=None, alpha=0.4):
        self.vertices = vertices
        self.color = color
        self.fill = fill
        self.alpha = alpha
    def __str__(self):
        base_str = super().__str__()
        return base_str

if __name__ == "__main__":
    polygon = Polygon((1, 2), (3, 4))
    print("polygon=" + str(polygon) + " id=" + hex(id(polygon)))