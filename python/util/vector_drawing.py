from math import sqrt, pi, ceil, floor
import numpy as np
import matplotlib
import matplotlib.patches
from matplotlib.collections import PatchCollection
from matplotlib.animation import FuncAnimation
import matplotlib.pyplot as plt
import vectors_z
from colorsZ import *
from mathModelZ import *
from time_z import *
from dir_z import *

def draw2d(*objects, origin=True, axes=True, grid=(1, 1), nice_aspect_ratio=True, width=12, save_as=None):
    fig = plt.figure()
    plt.grid(True)
    plt.axis('equal')  # 设置横纵坐标比例一致
    all_vectors = list(extract_vectors_2d(objects))
    xs, ys = zip(*all_vectors) # 使用 zip 函数将两个数组合并成元组. xs是元组()不是数组[]
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
        plt.gca().set_xticks(np.arange(plt.xlim()[0], plt.xlim()[1], grid[0]))
        plt.gca().set_yticks(np.arange(plt.ylim()[0], plt.ylim()[1], grid[1]))
        plt.gca().set_axisbelow(True)
    if origin:
        plt.scatter([0], [0], color='k', marker='x')
    if axes:
        plt.gca().axhline(linewidth=2, color='k') # 这两个是坐标轴
        plt.gca().axvline(linewidth=2, color='k')
    for object in objects:
        if type(object) == Polygon2D:
            for i in range(0, len(object.vertices)):
                x1, y1 = object.vertices[i]
                x2, y2 = object.vertices[(i + 1) % len(object.vertices)]
                plt.plot([x1, x2], [y1, y2], color=object.color)
            if object.fill:
                xs = [v[0] for v in object.vertices]
                ys = [v[1] for v in object.vertices]
                plt.gca().fill(xs, ys, object.fill, alpha=object.alpha)
        elif type(object) == Points2D:
            xs = [v[0] for v in object.vectors]
            ys = [v[1] for v in object.vectors]
            plt.scatter(xs, ys, color=object.color)
        elif type(object) == Arrow2D:
            tip, tail = object.tip, object.tail
            tip_length = (plt.xlim()[1] - plt.xlim()[0]) / 150.
            length = sqrt((tip[1] - tail[1]) ** 2 + (tip[0] - tail[0]) ** 2)
            new_length = length - tip_length
            new_y = (tip[1] - tail[1]) * (new_length / length)
            new_x = (tip[0] - tail[0]) * (new_length / length)
            plt.gca().arrow(tail[0], tail[1], new_x, new_y, head_width=tip_length / 1.5, head_length=tip_length, fc=object.color, ec=object.color)
        elif type(object) == Segment2D:
            x1, y1 = object.start_point
            x2, y2 = object.end_point
            plt.plot([x1, x2], [y1, y2], color=object.color)
        elif type(object) == Cos or type(object) == Sin:
            plt.plot(object.xs, object.ys)
        else:
            raise TypeError("Unrecognized object: {}".format(object))
    # fig = plt.gcf() # “get current figure”（获取当前图形）. and plt.gca()=“get current axes”（获取当前坐标轴）。
    """
    AxesSubplot(0.125,0.11;0.775x0.77) 表示当前图形中的坐标轴（Axes）的位置和大小。
    0.125,0.11：这两个数字表示坐标轴左下角在图像中的相对位置。0.125 是从左边边缘开始计算的宽度比例，0.11 是从底部边缘开始计算的高度比例。这意味着坐标轴的左下角位于整个图像区域的 12.5% 宽度和 11% 高度处。
    0.775x0.77：这部分表示坐标轴的宽度和高度，也是相对于整个图像区域的比例。0.775 表示坐标轴的宽度占整个图像宽度的 77.5%，0.77 表示坐标轴的高度占整个图像高度的 77%。
    """
    print("fig="+str(fig) + " fig2=" + str(plt.gcf()) + " gca=" + str(plt.gca()))
    if nice_aspect_ratio:
        coords_height = (plt.ylim()[1] - plt.ylim()[0])
        coords_width = (plt.xlim()[1] - plt.xlim()[0])
        fig.set_size_inches(width, width * coords_height / coords_width)
    if save_as:
        print("save matplotlib start")
        plt.savefig(save_as, dpi=300, bbox_inches='tight')
        print("save matplotlib end")
    plt.show()


def draw3d(*objects, origin=True, axes=True, width=6, save_as=None, azim=None, elev=None, xlim=None, ylim=None, zlim=None, xticks=None, yticks=None, zticks=None, depthshade=False):
    fig = plt.figure(4812094)
    ax = fig.add_subplot(111, projection='3d')
    ax.view_init(elev=elev, azim=azim)
    all_vectors = list(extract_vectors_3D(objects))
    if origin:
        all_vectors.append((0, 0, 0))
    xs, ys, zs = zip(*all_vectors)

    max_x, min_x = max(0, *xs), min(0, *xs)
    max_y, min_y = max(0, *ys), min(0, *ys)
    max_z, min_z = max(0, *zs), min(0, *zs)

    x_size = max_x - min_x
    y_size = max_y - min_y
    z_size = max_z - min_z

    padding_x = 0.05 * x_size if x_size else 1
    padding_y = 0.05 * y_size if y_size else 1
    padding_z = 0.05 * z_size if z_size else 1

    plot_x_range = (min(min_x - padding_x, -2), max(max_x + padding_x, 2))
    plot_y_range = (min(min_y - padding_y, -2), max(max_y + padding_y, 2))
    plot_z_range = (min(min_z - padding_z, -2), max(max_z + padding_z, 2))
    ax.set_xlabel('x')
    ax.set_ylabel('y')
    ax.set_zlabel('z')

    def draw_segment(start, end, color=black, linestyle='solid'):
        xs, ys, zs = [[start[i], end[i]] for i in range(0, 3)]
        ax.plot(xs, ys, zs, color=color, linestyle=linestyle)

    if axes:
        draw_segment((plot_x_range[0], 0, 0), (plot_x_range[1], 0, 0))
        draw_segment((0, plot_y_range[0], 0), (0, plot_y_range[1], 0))
        draw_segment((0, 0, plot_z_range[0]), (0, 0, plot_z_range[1]))

    if origin:
        ax.scatter([0], [0], [0], color='k', marker='x')

    for object in objects:
        if type(object) == Points3D:
            xs, ys, zs = zip(*object.vectors)
            ax.scatter(xs, ys, zs, color=object.color, depthshade=depthshade)

        elif type(object) == Polygon3D:
            for i in range(0, len(object.vertices)):
                draw_segment(
                    object.vertices[i],
                    object.vertices[(i + 1) % len(object.vertices)],
                    color=object.color)

        elif type(object) == Arrow3D:
            xs, ys, zs = zip(object.tail, object.tip)
            a = FancyArrow3D(xs, ys, zs, mutation_scale=20, arrowstyle='-|>', color=object.color)
            ax.add_artist(a)

        elif type(object) == Segment3D:
            draw_segment(object.start_point, object.end_point, color=object.color, linestyle=object.linestyle)

        elif type(object) == Box3D:
            x, y, z = object.vector
            kwargs = {'linestyle': 'dashed', 'color': 'gray'}
            draw_segment((0, y, 0), (x, y, 0), **kwargs)
            draw_segment((0, 0, z), (0, y, z), **kwargs)
            draw_segment((0, 0, z), (x, 0, z), **kwargs)
            draw_segment((0, y, 0), (0, y, z), **kwargs)
            draw_segment((x, 0, 0), (x, y, 0), **kwargs)
            draw_segment((x, 0, 0), (x, 0, z), **kwargs)
            draw_segment((0, y, z), (x, y, z), **kwargs)
            draw_segment((x, 0, z), (x, y, z), **kwargs)
            draw_segment((x, y, 0), (x, y, z), **kwargs)
        else:
            raise TypeError("Unrecognized object: {}".format(object))

    if xlim and ylim and zlim:
        plt.xlim(*xlim)
        plt.ylim(*ylim)
        ax.set_zlim(*zlim)
    if xticks and yticks and zticks:
        plt.xticks(xticks)
        plt.yticks(yticks)
        ax.set_zticks(zticks)

    if save_as:
        plt.savefig(save_as)
    plt.show()

def test_draw_easy_math(save_as=None):
    datax = np.linspace(0, 30, 200) # [0,`10]区间的线性增长的100个数字,  # xsin是数组[]不是元组()
    cos = Cos(datax)
    sin = Sin(datax)
    draw2d(Points2D((1, 2), (3, 4)), Segment2D((5, 6), (7, 8)), Polygon2D((-1, 0), (-2, -2), (0, -2)), Arrow2D((2, -3), tail=(4, -5)), cos, sin, save_as = save_as)

def test_draw_many_dinosaur(save_as=None):
    dino_vectors = [(6, 4), (3, 1), (1, 2), (-1, 5), (-2, 5), (-3, 4), (-4, 4),
        (-5, 3), (-5, 2), (-2, 2), (-5, 1), (-4, 0), (-2, 1), (-1, 0), (0, -3),
        (-1, -4), (1, -4), (2, -3), (1, -2), (3, -1), (5, 1)
    ]
    translations = [(12*x, 12*y) for x in range(-5, 5) for y in range(-5, 5)] # [(-60, -60), (-60, -48),..., (48,48)]
    dinos_vectors_all = [vectors_z.translate(t, dino_vectors) for t in translations]
    # [
        # [(), ()],一行代表一个dinosaur
        # [(), ()]
    # ]
    dinos = [Polygon2D(*v, color = blue) for v in dinos_vectors_all]
    locali = 0
    while(locali == 0):
        dinos_polar = [[vectors_z.to_polar(v) for v in vectorsss] for vectorsss in dinos_vectors_all]
        dinos_rotated_polar = [[(l, angle + locali) for (l, angle) in mmm] for mmm in dinos_polar]
        dinos_ratated = [[vectors_z.to_cartesian(p) for p in fff] for fff in dinos_rotated_polar]
        # [
        #     [(), ()],
        #     [(), ()],
        # ]
        dinos = [Polygon2D(*v, color=blue) for v in dinos_ratated] # * 操作符用于解包（unpacking）
        draw2d(*dinos, grid=None, axes=None, width=8, save_as = save_as)
        locali = locali + 0.01
        if locali >= 3.14:
            locali = 0

def draw_anim_test():
    x = np.linspace(0, 10, 100)
    y = np.sin(x)
    fig, ax = plt.subplots()
    plt.grid(True) # must below fig, otherwise grid will not show
    fig.set_size_inches(12, 8)
    ax.set_aspect('equal', 'box') # 保持纵横比一致，使圆形看起来更圆。
    ax.set_xlim(-10, 10)
    ax.set_ylim(-10, 10)
    line, = ax.plot(x, y, lw=1)  # 初始线宽为 1

    theta = np.linspace(0, 2 * np.pi, 100)  # 角度从 0 到 2π
    r = 5  # 半径
    x3 = r * np.cos(theta)
    y3 = r * np.sin(theta)
    line3, = ax.plot(x3, y3, lw=1)  # 初始曲线

    x4 = [1, 0]  # 初始线段的 x 坐标
    y4 = [0, 0]  # 初始线段的 y
    line4, = ax.plot(x4, y4, lw=1)  # 初始曲线

    def update(frame):
        if frame < 180:
            linewidth = 1 + (10 - 1) * (frame / 180)
        else:
            linewidth = 10 - (10 - 1) * ((frame - 180) / 180)
        line.set_linewidth(linewidth)

        angle = frame * np.pi / 180  # 每帧旋转 1 度
        rotation_matrix = np.array([
            [np.cos(angle), -np.sin(angle)],
            [np.sin(angle), np.cos(angle)]
        ])
        rotated_x = rotation_matrix[0, 0] * x3 + rotation_matrix[0, 1] * y3
        rotated_y = rotation_matrix[1, 0] * x3 + rotation_matrix[1, 1] * y3
        line3.set_data(rotated_x, rotated_y) # 这个啥用？写不写都是绘制一个圆
        rotated_x4 = rotation_matrix[0, 0] * x4[0] + rotation_matrix[0, 1] * y4[0]
        rotated_y4 = rotation_matrix[1, 0] * x4[0] + rotation_matrix[1, 1] * y4[0]
        line4.set_data([0, rotated_x4], [0, rotated_y4]) # 线段的一个端点固定在原点 (0, 0)，另一个端点在 (rotated_x, rotated_y)

        return line, line3, line4
    ani = FuncAnimation(fig, update, frames=np.arange(0, 360), interval=50, blit=True)
    # ani.save(f"{os.path.join(getMostRoot(), 'build/pic_math/')}/anim_{timeformat()}.mp4", writer='ffmpeg', fps=10)
    # ani.save(f"{os.path.join(getMostRoot(), 'build/pic_math/')}/anim_{timeformat()}.gif", writer='pillow', fps=10)
    plt.show()
    return ani

def draw_math_func(x, y):
    fig = plt.figure()
    plt.grid(True)
    plt.axis('equal') # 设置横纵坐标比例一致
    max_x, max_y, min_x, min_y = max(0, *x), max(0, *y), min(-0, *x), min(-0, *y)
    print("x=(" + str(min_x) + ", " + str(max_x) + ") y=(" + str(min_y) + ", " + str(max_y) + ")")
    plt.scatter([0], [0], color='k', marker='x')
    plt.xlim(min_x - 10, max_x + 10)
    plt.ylim(min_y - 10, max_y + 10)
    plt.gca().set_xticks(np.arange(plt.xlim()[0], plt.xlim()[1], 1)) # np.arange 生成了一个从当前x轴的最小值到最大值之间，步长为1的数组
    plt.gca().set_yticks(np.arange(plt.ylim()[0], plt.ylim()[1], 1))
    plt.gca().axhline(linewidth=1, color='k')  # 这两个是坐标轴
    plt.gca().axvline(linewidth=1, color='k')
    line, = plt.plot(x, y, lw=1)
    plt.show()

if __name__ == "__main__":
    plt.ion()
    datax = np.linspace(0, 10, 100)
    cos = Cos(datax)
    points = Points2D([1, 2], [3, 4])
    points2 = Points2D((1, 2), (3, 4))
    print("points=" + str(points) + " id=" + hex(id(points)) + " vector=" + str(points.vectors))
    print("points2=" + str(points2) + " id=" + hex(id(points2)) + " vector=" + str(points2.vectors))
    # gen = list(extract_vectors([Points((1, 2), (3, 4)), Segment((5,6), (7,8))]))
    # print(str(next(gen)))
    # print(str(next(gen)))
    # print(str(next(gen)))
    # print(str(next(gen)))
    all_vectors = list(extract_vectors_2d([Points2D((1, 2), (3, 4)), Segment2D((5, 6), (7, 8)), Polygon2D((-1, 0), (-2, -2), (0, -2)), Arrow2D((2, -3), tail=(4, -5)), cos]))
    # print(str((all_vectors)))
    print("draw start")
    test_draw_easy_math()
    ani_dinosaur = test_draw_many_dinosaur()
    ani = draw_anim_test()
    x = np.linspace(-10, 10, 100)
    # draw_math_func(x, (4 - x**3) ** (1/4))
    # draw_math_func(x, (1 - x ** (-1)) ** (-1))
    draw3d(Points3D((2,2,2),(1,-2,-2)))

    # 关闭交互模式（可选）
    plt.ioff()
    # 阻塞程序，等待用户关闭所有图形窗口
    plt.show()
    plt.close()
    print("draw end")
