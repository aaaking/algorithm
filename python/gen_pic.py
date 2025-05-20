
from  PIL import Image
import shutil
from util.logg import *
from util.dir_z import *
from util.time_z import *

DIR_PIC = os.path.join(getMostRoot(), "build/pic/")
print(f"pic dir={DIR_PIC}")



def checkPicDir():
    if os.path.exists(DIR_PIC):
        shutil.rmtree(DIR_PIC) #清空目录后再重新创建
    if not os.path.exists(DIR_PIC):
        print(f"pic dir not exist and create ret={os.makedirs(DIR_PIC)}")
    

def genAndSaveImage():
    width, height = 800, 600
    color = (255, 0, 0)
    image = Image.new("RGB", (width, height), color)
    imgName = f"{DIR_PIC}/pure_color_image_{timeformat()}.png"
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