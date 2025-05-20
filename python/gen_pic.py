
from  PIL import Image
import shutil
import sys
from util.logg import *
from util.dir_z import *
from util.time_z import *
import site

# 获取所有 site-packages 目录
site_packages = site.getsitepackages()

# 打印所有 site-packages 目录
# macbook m4电脑 输出包括位置:  /Applications/Xcode.app/Contents/Developer/Library/Frameworks/Python3.framework/Versions/3.9/lib/python3.9/site-packages 
#  卧槽 这里也有 python: /Library/Developer/CommandLineTools/Library/Frameworks/Python3.framework/Versions/3.9
# 但是实际的 pillow 的安装位置在: /Users/aaaking/Library/Python/3.9/lib/python/site-packages/pillow-11.2.1.dist-info

# 检查 pillow sdk 的安装情况
#  python3 -m pip list | grep pillow
# WARNING: You are using pip version 21.2.4; however, version 25.1.1 is available.
# You should consider upgrading via the '/Applications/Xcode.app/Contents/Developer/usr/bin/python3 -m pip install --upgrade pip' command.

# python 的位置设置应该因为下面两行的环境变量的代码导致的：
# python_path=$(python3 -c "import sys; print(sys.prefix)"); export PATH=$PATH:$python_path/bin
# user_python_path=$(dirname $(dirname $(dirname $(python3 -m site --user-site)))); export PATH=$PATH:$user_python_path/bin

log_green("打印 site_packages ")
for path in site_packages:
    print(path)

DIR_PIC = os.path.join(getMostRoot(), "build/pic/")
print(f"pic dir={DIR_PIC}")


log_yellow(f"PIL sdk 路径是：{os.path.dirname(Image.__file__)}")


# 从这里的数组的目录中查找sdk
log_green("打印 sdk 查找路径, or say it 系统路径, sys.path=") # 可以手动添加路径: sys.path.append('/Users/aaaking/Library/Python/3.9/lib/python/site-packages')
log_cyan(f"sys.path={sys.path}")
log_green(f"sys.prefix={sys.prefix}")




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