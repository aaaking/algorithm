from colorsZ import *
import numpy as np

class Polygon2D():
    def __init__(self, *vertices, color=blue, fill=None, alpha=0.4):
        self.vertices = vertices
        self.color = color
        self.fill = fill
        self.alpha = alpha
    def __str__(self):
        base_str = super().__str__()
        return base_str + "\n" + str(self.vertices)

class Points2D():
    def __init__(self, *vectors, color=black): # 不带星号呢？
        self.vectors = list(vectors)
        self.color = color

class Arrow2D:
    def __init__(self, tip, tail = (0, 0), color = red):
        self.tip = tip
        self.tail = tail
        self.color = color

class Segment2D():
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
# helper function to extract all the vectors from a list of objects
def extract_vectors_2d(objects):
    for object in objects:
        typeObj = type(object)
        if typeObj == Polygon2D:
            for v in object.vertices:
                yield v
        elif typeObj == Points2D:
            for v in object.vectors:
                yield v
        elif typeObj == Arrow2D:
            yield object.tip
            yield object.tail
        elif typeObj == Segment2D:
            yield object.start_point
            yield object.end_point
        elif typeObj == Cos or typeObj == Sin:
            for v in zip(object.xs, object.ys):
                yield v
        else:
            raise TypeError("Unrecognized object: {}".format(object))

if __name__ == "__main__":
    coordinate_sums = [1,2,3,4,45,5]
    fff = tuple(coordinate_sums)
    print("fff=" + str(fff))