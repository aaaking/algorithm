#!/usr/bin/env python
# coding=utf-8
# encoding = utf-8
import requests, json
import sys
import zipfile
import os.path
import os
import hashlib
import shutil
from requests_toolbelt.multipart.encoder import MultipartEncoder

# pip3 install requests_toolbelt --break-system-packages

TAG = "SOSPLIT:"
TEMP_AAR = "tmp_aar"
SO_NAME = "libaemonplayer_aio.so"

class AbiPacks:
    def __init__(self, soMd5, md5, url):
        self.soMd5 = soMd5
        self.md5 = md5
        self.version = md5
        self.url = url

class OutJson:
    def __init__(self, plugins):
        self.plugins = plugins


class Plugins:
    def __init__(self, pluginId, enable, abiPacks):
        self.abiPacks = abiPacks
        self.pluginId = pluginId
        self.enable = enable

class SoBean:
    def __init__(self, soMd5, soName):
        self.soMd5 = soMd5
        self.soName = soName

def splitUpload():
    # 打印传递过来的参数数组长度，便于校验
    deleteKwaiPlayerAioSo = 'false'
    if len(sys.argv) > 1:
        deleteKwaiPlayerAioSo = sys.argv[1]
    moduelName = 'ksmediaplayer'
    rootPath = os.path.abspath('.') + '/' + moduelName
    print(TAG + 'delete kwaiPlayerAioSo=' + deleteKwaiPlayerAioSo + ", rootpath=" + rootPath)
    arm64path = rootPath + f"/{TEMP_AAR}/jni/arm64-v8a/"
    armv7path = rootPath + f'/{TEMP_AAR}/jni/armeabi-v7a/'
    jsonFile = rootPath + f'/{TEMP_AAR}/assets/sodler.json'
    apkJsonFile = os.path.abspath('.') + '/app/src/main/assets/sodler.json'
    aarPath = rootPath +'/build/outputs/aar/ksmediaplayer-release.aar'
    dstaarPath = os.path.abspath('.') + '/app/libs/ksmediaplayer-release.aar'
    uploadMulFileUrl = 'https://kcdn.corp.kuaishou.com/api/kcdn/v1/service/npmUpload/multiple?token=10924_31d3065e88d32dc9f80d7d664dd2af47'

    # step 1: unzip aar
    unzip_file(aarPath, rootPath +f'/{TEMP_AAR}')

    # step 2: zip v7/v8 so
    arm64_so_md5 = getMd5(arm64path + SO_NAME)
    zipDir(rootPath + f'/{TEMP_AAR}/jni/arm64-v8a', rootPath + '/temp-arm-64.zip', SO_NAME, '/lib/arm64-v8a')
    armv7_so_md5 = getMd5(armv7path + SO_NAME)
    zipDir(rootPath + f'/{TEMP_AAR}/jni/armeabi-v7a', rootPath + '/temp-arm-v7.zip', SO_NAME,'/lib/armeabi-v7a')
    arm64_zip_md5 = getMd5(rootPath + '/temp-arm-64.zip')
    armv7_zip_md5 = getMd5(rootPath + '/temp-arm-v7.zip')
    print(TAG + 'arm64_zip_md5: ' + arm64_zip_md5)
    print(TAG + 'arm64_so_md5: ' + arm64_so_md5)
    print(TAG + 'armv7_zip_md5: ' + armv7_zip_md5)
    print(TAG + 'armv7_so_md5: ' + armv7_so_md5)

    # step 3: upload zip
    params = {
        'pid': 'toB-KSMediaPlayer'
        # 'uid': 'test',
        # 'allowRewrite':'true'
    }
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0',
        'Referer': uploadMulFileUrl
    }

    m = MultipartEncoder([('files[]', ('temp-arm-64_' + arm64_zip_md5 + '.zip' , open(rootPath + '/temp-arm-64.zip', 'rb'))),
                      ('files[]', ('temp-arm-v7_' + armv7_zip_md5 + '.zip' , open(rootPath + '/temp-arm-v7.zip', 'rb')))])
    # print(TAG + "upload req:")
    # print(m)
    # r = requests.request('post',uploadMulFileUrl, files=m.fields,params=params)
    # print(TAG + "upload rep:")
    # print(r.text)
    #
    # # step 4: handle .json file
    # uploadResult = json.loads(r.text)
    url64 = "url64"#uploadResult['data']['fileResults'][0]['cdnUrl']
    url32 = "url32"#uploadResult['data']['fileResults'][1]['cdnUrl']
    soBean32 = SoBean(armv7_so_md5, SO_NAME)
    soBean64 = SoBean(arm64_so_md5, SO_NAME)
    abiPackv7 = AbiPacks([soBean32], armv7_zip_md5, url32)
    abiPack64 = AbiPacks([soBean64], arm64_zip_md5, url64)
    abiPackList = {'armeabi-v7a': abiPackv7, 'arm64-v8a': abiPack64}
    plugin = Plugins('kwaiplayer', True, abiPackList)
    outJson = OutJson([plugin])
    json_str = json.dumps(outJson, default=lambda o: o.__dict__, sort_keys=True, indent=4)
    del_file(jsonFile)
    f = open(jsonFile, mode="w")
    f.write(json_str)
    f.close()
    # write apk json
    f = open(apkJsonFile, mode="w")
    f.write(json_str)
    f.close()
    if deleteKwaiPlayerAioSo == 'true':
        del_file(rootPath+ f'/{TEMP_AAR}/jni/arm64-v8a/{SO_NAME}')
        del_file(rootPath+ f'/{TEMP_AAR}/jni/armeabi-v7a/{SO_NAME}')
    zipFullDir(rootPath + f'/{TEMP_AAR}', dstaarPath)
    del_file(rootPath + "/temp-arm-64.zip")
    del_file(rootPath + "/temp-arm-v7.zip")
    # del_file(rootPath + f"/{TEMP_AAR}")
    print(TAG + 'end')


def zipDir(dirpath, outFullName, filename, filepath):
    zip = zipfile.ZipFile(outFullName,"w",zipfile.ZIP_DEFLATED)
    for path,dirnames,filenames in os.walk(dirpath):
        # 去掉目标跟路径，只对目标文件夹下边的文件及文件夹进行压缩
        # fpath = path.replace(dirpath,'')
        for f in filenames:
            if f == filename:
                zip.write(os.path.join(path,f),os.path.join(filepath,f))
    zip.close()

def zipFullDir(dirpath, outFullName):
    zip = zipfile.ZipFile(outFullName,"w",zipfile.ZIP_DEFLATED)
    for path,dirnames,filenames in os.walk(dirpath):
        for f in filenames:
            fPath = path.replace(dirpath,'')
            zip.write(os.path.join(path,f),os.path.join(fPath ,f))
    zip.close()
    print('zip full done')

def unzip_file(fz_name, path):
    print(TAG + "unzip_file start:" + fz_name)
    if zipfile.is_zipfile(fz_name):  # 检查是否为zip文件
        try:
            with zipfile.ZipFile(fz_name, 'r') as zipf:
                zipf.extractall(path)
                # for p in zipf.namelist():
                #     # 使用cp437对文件名进行解码还原， win下一般使用的是gbk编码
                #     p = p.encode('cp437').decode('gbk')  # 解决中文乱码
                #     print(fz_name, p,path)
        except Exception as e:
            print(TAG + '解压文件[{}]失败')
    else:
        print(TAG + '不是zip文件')
    print(TAG + 'unzip_file end')

def getMd5(f_name):
    if os.path.exists(f_name):
        md5_l = hashlib.md5()
        with open(f_name,mode="rb") as f:
            by = f.read()
        md5_l.update(by)
        return md5_l.hexdigest()
    else:
        return 'md5 file not exit'

def getMd5FromStr(str):
    hl = hashlib.md5()
    hl.update(str.encode(encoding='utf-8'))
    return hl.hexdigest()

def del_file(filepath):
    if os.path.exists(filepath):
        if os.path.isdir(filepath):
            del_list = os.listdir(filepath)
            for f in del_list:
                file_path = os.path.join(filepath, f)
                if os.path.isfile(file_path):
                    os.remove(file_path)
                elif os.path.isdir(file_path):
                    shutil.rmtree(file_path)
            shutil.rmtree(filepath)
        else:
            os.remove(filepath)
    else:
        parent_dir = os.path.dirname(filepath)
        # 如果父目录不存在，则创建它
        if not os.path.exists(parent_dir):
            os.makedirs(parent_dir)


if __name__ == '__main__':
    splitUpload()

# {
#     "result": 1,
#     "data": {
#         "success": true,
#         "fileResults": [
#             {
#                 "status": 1,
#                 "cdnUrl": "https://s2-10924.kwimgs.com/kos/nlav10924/temp-arm-64_cddb6653853cf1942d6372563f702a87.zip",
#                 "message": ""
#             },
#             {
#                 "status": 1,
#                 "cdnUrl": "https://s2-10924.kwimgs.com/kos/nlav10924/temp-arm-v7_81168080e31e7ca2e0638f812a207f59.zip",
#                 "message": ""
#             }
#         ]
#     },
#     "message": "",
#     "code": 0
# }
