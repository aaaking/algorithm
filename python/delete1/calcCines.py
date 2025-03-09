import csv
import json
import codecs
import io
import os

lines = 0
targetDirectory = "/Users/zhouzhihui/codebase/workCode/youka/code/mjz_client/src"

def row_count(filename):
    with open(filename) as in_file:
        return sum(1 for _ in in_file)


g = os.walk(r"/Users/zhouzhihui/codebase/workCode/youka/code/mjz_client/src")

for path, dir_list, file_list in g:
    for file in file_list:
        lines += row_count(os.path.join(path, file))

print(lines)
