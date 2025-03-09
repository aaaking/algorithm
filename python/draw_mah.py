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

import threading
from math import sqrt, pi, ceil, floor
import matplotlib
import matplotlib.patches
import matplotlib.pyplot as plt
from matplotlib.collections import PatchCollection
from matplotlib.pyplot import xlim, ylim
import numpy as np
import util.logg as logg
from util.dir import *
from util.time import *

DIR_MATH_PIC = os.path.join(getMostRoot(), "build/pic_math/")

def checkMathDir():
    if not os.path.exists(DIR_MATH_PIC):
        print(f"math pic dir not exist and create ret={os.makedirs(DIR_MATH_PIC)}")

def draw_bar():
    categories = ['A', 'B', 'C', 'D']
    values = [3, 7, 2, 5]
    plt.bar(categories, values)
    plt.title("BasicBar")
    plt.xlabel("Category")
    plt.ylabel("value")
    for i in range(len(categories)):
        plt.text(i, values[i], str(values[i]), ha='center', va='bottom')

    # plt.show(block = False) # 这个方法，其他代码必须放在pause之前 才不会被阻塞，draw方法也有这个问题
    plt.draw()
    imgName = f"{DIR_MATH_PIC}/basic_bar_savefig_{timeformat()}.png"
    plt.savefig(imgName, dpi=300, bbox_inches='tight')
    plt.pause(-1)  # 保持图形窗口打开10秒, -1 infinite

    def show_plot_subthread():
        # plt.show() # Terminating app due to uncaught exception 'NSInternalInconsistencyException', reason: 'NSWindow drag regions should only be invalidated on the Main Thread!'
        logg.log_red("cannot show image in thread which is not main thread=" + str(threading.currentThread()) + " is daemon=" + str(threading.currentThread().isDaemon()))
    plot_thread = threading.Thread(target=show_plot_subthread)
    plot_thread.start()
    plot_thread.join()

checkMathDir()
if __name__ == '__main__':
    logg.log_cyan("draw math start thread=" + str(threading.currentThread()) + " is daemon=" + str(threading.currentThread().isDaemon()))
    draw_bar()
    logg.log_green("draw math end")
