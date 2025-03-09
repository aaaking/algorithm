
from  PIL import Image
import os
import time
import shutil
import argparse
import sys
import re
import datetime

cmd_dir = os.getcwd() # 执行python命令的目录
script_dir = os.path.abspath(os.path.dirname(__file__)) # or os.path.dirname(os.path.abspath(__file__)) # 脚本所在的目录。
DIR_PIC = os.path.join(script_dir, "build/pic/")
print(f"cmd dir={cmd_dir} \n script dir={script_dir} \n pic dir={DIR_PIC}")

# eyeD3：这是一个Python库，可以方便地读取和修改ID3v2标签。安装方法如下：
# pip3 install eyed3
# Mutagen：另一个强大的Python库，支持多种音频文件的元数据操作。安装方法如下：
# pip3 install mutagen

# 使用eyeD3读取ID3v2标签的示例代码：
# import eyed3
# def read_id3v2(file_path):
#     audiofile = eyed3.load(file_path)
#     if audiofile.tag:
#         print("Title:", audiofile.tag.title)
#         print("Artist:", audiofile.tag.artist)
#         print("Album:", audiofile.tag.album)
#         print("Year:", audiofile.tag.release_date)
#         print("Genre:", audiofile.tag.genre)
#         print("Comments:", audiofile.tag.comments)
#         print("Images:", audiofile.tag.images)
#     else:
#         print("No ID3v2 tag found")


# 使用Mutagen读取ID3v2标签的示例代码：
# from mutagen.id3 import ID3
# def read_id3v2(file_path):
#     audio = ID3(file_path)
#     for key, value in audio.items():
#         print(f"{key}: {value}")


def read_id3v1(file_path):
    with open(file_path, 'rb') as f:
        f.seek(-128, 2)  # 移动到文件末尾128个字节
        tag = f.read(128)
    if tag[:3] == b'TAG':
        title = tag[3:33].strip(b'\x00').decode('utf-8', errors='ignore')
        artist = tag[33:63].strip(b'\x00').decode('utf-8', errors='ignore')
        album = tag[63:93].strip(b'\x00').decode('utf-8', errors='ignore')
        year = tag[93:97].strip(b'\x00').decode('utf-8', errors='ignore')
        comment = tag[97:127].strip(b'\x00').decode('utf-8', errors='ignore')
        track = tag[127]
        return {
            'title': title,
            'artist': artist,
            'album': album,
            'year': year,
            'comment': comment,
            'track': track
        }
    else:
        return None

def read_id3v2_header(file_path):
    with open(file_path, 'rb') as f:
        header = f.read(10)
    if header[:3] == b'ID3':
        version = header[3]
        subversion = header[4]
        flags = header[5]
        size = (header[6] << 21) | (header[7] << 14) | (header[8] << 7) | header[9]
        return {
            'version': version,
            'subversion': subversion,
            'flags': flags,
            'size': size
        }
    else:
        return None

if __name__ == '__main__':
    # aff = f'ID3'
    # afg = b'ID3'
    # print(aff)
    # print(afg)
    # aff_bytes = aff.encode('utf-8')
    # print(aff_bytes)
    # print(aff_bytes == afg)
    # afg_str = afg.decode('utf-8')
    # print(afg_str)
    # print(afg_str == aff)
    # parser = argparse.ArgumentParser(description='命令参数信息')
    # parser.add_argument('-u', type=str, help='path')
    # args = parser.parse_args()
    print(f"sys.argv={(sys.argv)}")
    # print(f"args={args}")
    # if args.u is None:
    #     help = """
    #     请输入正确参数：.py-u <url>
    #     """
    #     print(help)
    # else:
    #     print("start，参数为：")
    #     print(f"url：{args.u}")

    if len(sys.argv) > 1:
        file_path = sys.argv[1]
        print(f"start<>file_path{file_path}")
        os.chmod(file_path, 0o777)
        print(f"end<>file_path{file_path}")
        id3v2 = read_id3v2_header(file_path)
        print(id3v2)
    else:
        print("./*.py <文件路径>")


    
