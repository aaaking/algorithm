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
import multiprocessing
import sys
import threading
from math import sqrt, pi, ceil, floor
import matplotlib
import matplotlib.patches
import matplotlib.pyplot as plt
from matplotlib.pyplot import xlim, ylim
from matplotlib.collections import PatchCollection
import numpy as np
import util.logg as logg
from util.dir import *
from util.time import *

# matplotlib.rcParams['font.sans-serif'] = ['SimHei'] # 设置全局字体为 SimHei（黑体）。
DIR_MATH_PIC = os.path.join(getMostRoot(), "build/pic_math/")

def checkMathDir():
    if not os.path.exists(DIR_MATH_PIC):
        print(f"math pic dir not exist and create ret={os.makedirs(DIR_MATH_PIC)}")

def draw_barv(categories = ['A', 'B', 'C', 'D'], values = [3, 7, 2, 5]):
    print("categories=" + str(categories) + " values=" + str(values))
    values1 = [3, 7, 2, 5]
    values2 = [4, 6, 3, 4]
    values3 = [6, 8, 9, 9]
    x = np.arange(len(categories))
    width = 0.35
    # 创建分组柱状图
    fig, ax = plt.subplots()
    rects1 = ax.bar(x - width / 2, values1, width, label='Group 1')
    rects2 = ax.bar(x + width / 2, values2, width, label='Group 2')
    rects3 = ax.bar(x + width / 2, values3, bottom=values2, width = width, label='Group 3')
    ax.set_title("GroupBar", fontproperties="SimSun")
    ax.set_xlabel('Category')
    ax.set_ylabel('Value')
    ax.set_xticks(x)
    ax.set_xticklabels(categories)
    ax.legend()
    # 显示数值
    def add_labels(rects):
        for rect in rects:
            # logg.log_red(str(rect))
            height = rect.get_height()
            posY = rect.get_y() + height
            ax.annotate('{}'.format(height),
                        xy=(rect.get_x() + rect.get_width() / 2, posY),
                        xytext=(0, 0.5),  # 0.5 points vertical offset
                        textcoords="offset points",
                        ha='center', va='bottom')
    add_labels(rects1)
    add_labels(rects2)
    add_labels(rects3)

    # plt.bar(categories, values, color=['blue', 'green', 'red', 'purple'], width=0.5) # also see barh
    # plt.title("中文乱码", fontproperties="SimSun")
    # plt.xlabel("Category")
    # plt.ylabel("Value")
    # for i in range(len(categories)):
    #     plt.text(i, values[i], str(values[i]), ha='center', va='bottom')

    def show_plot_subprocess():
        logg.log_red("subprocess show image thread=" + str(threading.currentThread()) + " is daemon=" + str(threading.currentThread().isDaemon()))
        # 设置后端为 Agg, 必须在导入 matplotlib.pyplot 之前调用，以确保后端正确设置。Agg 后端是非交互式的，适用于在没有图形界面的环境中生成图像文件。
        # matplotlib.use('Agg')
        # 切换后端为 Agg, 可以在导入 matplotlib.pyplot 之后调用，动态切换后端。这种方法在某些情况下可能更灵活，但需要注意后端切换的时机。
        # plt.switch_backend('Agg')
        # plt.bar(["RR", "TT"], [1000, -10]) # 这个柱状图会和住进程的追加
        # plt.show()  # not work, show nothing, but savefig works
        import time
        time.sleep(2)
        logg.log_red("subprocess exit")
        sys.exit()
    # 创建并启动进程
    plot_process = multiprocessing.Process(target=show_plot_subprocess)
    plot_process.start()

    # plt.show(block = False) # 这个方法，其他代码必须放在pause之前 才不会被阻塞，draw方法也有这个问题
    plt.draw()
    logg.log_pink("save fig start")
    imgName = f"{DIR_MATH_PIC}/basic_bargroup_savefigw=0.35_{timeformat()}.png"
    # plt.savefig(imgName, dpi=300, bbox_inches='tight')
    logg.log_pink("save fig end")
    plt.pause(-1)  # 保持图形窗口打开10秒, -1 infinite

    def show_plot_subthread():
        # plt.show() # Terminating app due to uncaught exception 'NSInternalInconsistencyException', reason: 'NSWindow drag regions should only be invalidated on the Main Thread!'
        logg.log_red("cannot show image in thread which is not main thread=" + str(threading.currentThread()) + " is daemon=" + str(threading.currentThread().isDaemon()))
    plot_thread = threading.Thread(target=show_plot_subthread)
    plot_thread.start()
    plot_thread.join()
    plot_process.join()
    plt.close()

checkMathDir()
if __name__ == '__main__':
    logg.log_cyan("draw math start thread=" + str(threading.currentThread()) + " is daemon=" + str(threading.currentThread().isDaemon()))
    draw_barv()
    logg.log_green("draw math end")
