import csv
import json
import codecs
import io
import os

fileName = 'compoundData.csv'

def row_count(filename):
    with open(filename) as in_file:
        return sum(1 for _ in in_file)


def length(obj):
    return sum(1 for _ in obj)


csvfile = open(fileName, 'r')
jsonfile = open("compoundData.json", 'w') # io.open('filename', 'w', encoding='utf8')

fieldnames = ("id", "name", "icon", "camp", "desc", "star", "storage")
reader = csv.DictReader(csvfile, fieldnames)

last_line_number = row_count(fileName)
last_column_number = length(fieldnames)


jsonfile.write('{\n')

for row in reader:
    content = u"\"" + row['id'] + "\":"
    jsonfile.write(content)
    # common = json.dumps(row, False)
    # print json.loads(common)['name']
    # print common
    # jsonfile.write(unicode(common))
    column = 0
    jsonfile.write('{')
    for item in fieldnames:
        column += 1
        jsonfile.write('\"' + item + "\":\"" + row[item] + "\"")
        if column == last_column_number:
            jsonfile.write(',\"package\":\"1\"}')
        else:
            jsonfile.write(',')
    if last_line_number == reader.line_num:
        jsonfile.write('\n')
    else:
        jsonfile.write(',\n')

jsonfile.write('}')
