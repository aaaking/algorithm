
from  PIL import Image

def genAndSaveImage():
    width, height = 800, 600
    color = (255, 0, 0)
    image = Image.new("RGB", (width, height), color)
    image.save("build/pic/pure_color_image.png")

if __name__ == '__main__':
    genAndSaveImage()
    
