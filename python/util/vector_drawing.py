from math import sqrt, pi, ceil, floor
import matplotlib
import matplotlib.patches
from matplotlib.collections import PatchCollection
import matplotlib.pyplot as plt
import numpy as np

blue = 'C0'
black = 'k'
red = 'C3'
green = 'C2'
purple = 'C4'
orange = 'C2'
gray = 'gray'

"""
yield 关键字在 Python 中用于定义生成器（generator）。生成器是一种特殊的迭代器，它允许你在函数内部逐步生成值，而不是一次性生成所有值并返回一个完整的列表。这使得生成器在处理大量数据时特别有用，因为它可以节省内存并提高性能。
yield 的主要作用
1. 延迟计算：
生成器不会一次性计算所有的值，而是在每次调用 next() 方法时计算下一个值。这意味着你可以逐步处理数据，而不是一次性加载所有数据。
2. 节省内存：
由于生成器只在需要时生成值，因此它可以处理非常大的数据集，而不会因为一次性加载所有数据而导致内存不足。
3. 简化代码：
使用生成器可以使代码更加简洁和易读，因为你可以在一个函数中逐步生成值，而不是在一个函数中返回一个完整的列表。
"""

class Polygon():
    def __init__(self, *vertices, color=blue, fill=None, alpha=0.4):
        self.vertices = vertices
        self.color = color
        self.fill = fill
        self.alpha = alpha
    def __str__(self):
        base_str = super().__str__()
        return base_str

class Points():
    def __init__(self, *vectors, color=black):
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

# helper function to extract all the vectors from a list of objects
def extract_vectors(objects):
    for object in objects:
        typeObj = type(object)
        if typeObj == Polygon:
            for v in object.vertices:
                yield v
        elif typeObj == Points:
            for v in object.vectors:
                yield v
        elif typeObj == Arrow:
            yield object.tip
            yield object.tail
        elif typeObj == Segment:
            yield object.start_point
            yield object.end_point
        else:
            raise TypeError("Unrecognized object: {}".format(object))

def draw(*objects, origin=True, axes=True, grid=(1,1), nice_aspect_ratio=True, width=6, save_as=None):
    plt.figure()
    plt.grid(True)
    plt.plot([-99999999, 99999999], [0, 0], red) # 这两个是坐标轴
    plt.plot([0, 0], [-99999999, 99999999], red)
    all_vectors = list(extract_vectors(objects))
    xs, ys = zip(*all_vectors)
    max_x, max_y, min_x, min_y = max(0, *xs), max(0, *ys), min(0, *xs), min(0, *ys)
    if grid:
        x_padding = max(ceil(0.05 * (max_x - min_x)), grid[0])
        y_padding = max(ceil(0.05 * (max_y - min_y)), grid[1])
        def round_up_to_multiple(val, size):
            return floor((val + size) / size) * size
        def round_down_to_multiple(val, size):
            return -floor((-val - size) / size) * size
        plt.xlim(floor((min_x - x_padding) / grid[0]) * grid[0], ceil((max_x + x_padding) / grid[0]) * grid[0])
        plt.ylim(floor((min_y - y_padding) / grid[1]) * grid[1], ceil((max_y + y_padding) / grid[1]) * grid[1])

    x = np.linspace(0, 10, 100) # [0,`10]区间的线性增长的100个数字
    y = np.sin(x)
    plt.plot(x, y)

    plt.show()

if __name__ == "__main__":
    points = Points([1, 2], [3, 4])
    points2 = Points((1, 2), (3, 4))
    print("points=" + str(points) + " id=" + hex(id(points)) + " vector=" + str(points.vectors))
    print("points2=" + str(points2) + " id=" + hex(id(points2)) + " vector=" + str(points2.vectors))
    # gen = list(extract_vectors([Points((1, 2), (3, 4)), Segment((5,6), (7,8))]))
    # print(str(next(gen)))
    # print(str(next(gen)))
    # print(str(next(gen)))
    # print(str(next(gen)))
    all_vectors = list(extract_vectors([Points((1, 2), (3, 4)), Segment((5,6), (7,8))]))
    print(str((all_vectors)))
    print("draw start")
    draw(Points((1, 2), (3, 4)))
    print("draw end")
