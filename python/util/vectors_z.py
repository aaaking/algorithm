from math import sqrt, sin, cos, atan2, acos
import numpy as np

# def subtract(v1, v2):
#     return (v1[0] - v2[0], v1[1] - v2[1])
def subtract(v1,v2):
    return tuple(v1-v2 for (v1,v2) in zip(v1,v2))

# def add(*vectors):
#     return (sum([v[0] for v in vectors]), sum([v[1] for v in vectors]))
# def add(*vectors):
#     by_coordinate = zip(*vectors)
#     coordinate_sums = [sum(coords) for coords in by_coordinate]
#     return tuple(coordinate_sums)
def add(*vectors):
    return tuple(map(sum,zip(*vectors)))

def length(v):
    # return sqrt(v[0]**2 + v[1]**2)
    return sqrt(sum([coord ** 2 for coord in v]))

def distance(v1,v2):
    return length(subtract(v1,v2))

def dot(u,v):
    return sum([coord1 * coord2 for coord1,coord2 in zip(u,v)])

# 周长
def perimeter(vectors):
    distances = [distance(vectors[i], vectors[(i+1)%len(vectors)]) for i in range(0,len(vectors))]
    return sum(distances)

def scale(scalar,v):
    # return (scalar * v[0], scalar * v[1])
    return tuple(scalar * coord for coord in v)

def to_cartesian(polar_vector):
    length, angle = polar_vector[0], polar_vector[1]
    return (length * cos(angle), length * sin(angle))

def rotate(angle, vectors):
    polars = [to_polar(v) for v in vectors]
    return [to_cartesian((l, a + angle)) for l, a in polars]

def translate(translation, vectors):
    return [add(translation, v) for v in vectors]

def to_polar(vector):
    x, y = vector[0], vector[1]
    angle = atan2(y, x)
    return (length(vector), angle)

def angle_between(v1,v2):
    return acos(dot(v1,v2) /(length(v1) * length(v2)))

def cross(u, v):
    ux,uy,uz = u
    vx,vy,vz = v
    return (uy*vz - uz*vy, uz*vx - ux*vz, ux*vy - uy*vx)

def component(v,direction):
    return (dot(v,direction) / length(direction))

def unit(v):
    return scale(1./length(v), v)

if __name__ == "__main__":
    uuuN = 180 / np.pi
    print(str(angle_between((1,0), (0, 1)) * uuuN))