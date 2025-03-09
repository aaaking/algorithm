import time
import datetime

def timeformat():
    timestamp = (time.time() * 1000)
    dt = datetime.datetime.fromtimestamp(timestamp / 1000)
    line_with_time = dt.strftime('%Y-%m-%d %H:%M:%S.%f')
    return line_with_time