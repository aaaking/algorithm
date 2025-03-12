from colorsZ import *
import numpy as np

class Polygon():
    def __init__(self, *vertices, color=blue, fill=None, alpha=0.4):
        self.vertices = vertices
        self.color = color
        self.fill = fill
        self.alpha = alpha
    def __str__(self):
        base_str = super().__str__()
        return base_str + "\n" + str(self.vertices)

class Points():
    def __init__(self, *vectors, color=black): # 不带星号呢？
        self.vectors = list(vectors)
        self.color = color

class Arrow:
    def __init__(self, tip, tail = (0, 0), color = red):
        self.tip = tip
        self.tail = tail
        self.color = color

class Segment():
    def __init__(self, start_point, end_point, color=blue):
        self.start_point = start_point
        self.end_point = end_point
        self.color = color

class Sin():
    def __init__(self, xs):
        self.xs = xs
        self.ys = [np.sin(x) for x in self.xs]

class Cos():
    def __init__(self, xs):
        self.xs = xs
        self.ys = [np.cos(x) for x in self.xs]

if __name__ == "__main__":
    coordinate_sums = [1,2,3,4,45,5]
    fff = tuple(coordinate_sums)
    print("fff=" + str(fff))