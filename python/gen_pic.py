
from  PIL import Image
import os
import time
import shutil
import datetime
# import util.logg
from util.logg import *

cmd_dir = os.getcwd() # 执行python命令的目录
script_dir = os.path.abspath(os.path.dirname(__file__)) # or os.path.dirname(os.path.abspath(__file__)) # 脚本所在的目录。
DIR_PIC = os.path.join(script_dir, "build/pic/")
print(f"cmd dir={cmd_dir} \n script dir={script_dir} \n pic dir={DIR_PIC}")



def checkPicDir():
    if os.path.exists(DIR_PIC):
        shutil.rmtree(DIR_PIC) #清空目录后再重新创建
    if not os.path.exists(DIR_PIC):
        print(f"pic dir not exist and create ret={os.makedirs(DIR_PIC)}")
    

def genAndSaveImage():
    timestamp = (time.time() * 1000)
    dt = datetime.datetime.fromtimestamp(timestamp / 1000)
    line_with_time = dt.strftime('%Y-%m-%d %H:%M:%S.%f')
    width, height = 800, 600
    color = (255, 0, 0)
    image = Image.new("RGB", (width, height), color)
    imgName = f"{DIR_PIC}/pure_color_image_{line_with_time}.png"
    image.save(imgName)
    # showImageByFilePath(imgName)

def showImageByFilePath(path):
    # 打开一个图像文件
    image = Image.open(path)
    image.show()

if __name__ == '__main__':
    checkPicDir()
    genAndSaveImage()
    log_green("succ")