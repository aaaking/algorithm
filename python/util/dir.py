import os
def getMostRoot():
    cmd_dir = os.getcwd()  # 执行python命令的目录
    script_dir = os.path.abspath(os.path.dirname(__file__))  # or os.path.dirname(os.path.abspath(__file__)) # 脚本所在的目录。
    return os.path.abspath(os.path.join(script_dir, ".."))