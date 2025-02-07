
from  PIL import Image
import os
import time
import shutil

cmd_dir = os.getcwd() # 执行python命令的目录
script_dir = os.path.abspath(os.path.dirname(__file__)) # or os.path.dirname(os.path.abspath(__file__)) # 脚本所在的目录。
DIR_PIC = os.path.join(script_dir, "build/pic/")
print(f"cmd dir={cmd_dir} \n script dir={script_dir} \n pic dir={DIR_PIC}")



def checkPicDir():
    shutil.rmtree(DIR_PIC) #清空目录后再重新创建
    if not os.path.exists(DIR_PIC):
        print(f"pic dir not exist and create ret={os.makedirs(DIR_PIC)}")
    

def genAndSaveImage():
    timestamp = int(time.time() * 1000)
    width, height = 800, 600
    color = (255, 0, 0)
    image = Image.new("RGB", (width, height), color)
    image.save(f"{DIR_PIC}/pure_color_image_{timestamp}.png")
    # 打开一个图像文件
    # image = Image.open('example.jpg')
    # image.show()

if __name__ == '__main__':
    checkPicDir()
    genAndSaveImage()
    
