from flask import Flask, send_from_directory, request
import os
import logging

app = Flask(__name__)

@app.route('/')
def index():
    return send_from_directory('static', 'index.html')

@app.route('/trigger-adb')
def trigger_adb():
    mobile_number = request.args.get('mobileNumber') # default number if not provided
    pwd = request.args.get('pwd')
    logging.warning(f"start adb phone number={mobile_number}  and pwd={pwd}")
    print(f"start adb phone number is {mobile_number} and pwd is {pwd}")
    adb_command = f'adb shell input tap 250 460 && adb shell input text "{mobile_number}" && adb shell input tap 250 700 && adb shell input text "{pwd}" && adb shell input tap 280 900 && sleep 3 && adb shell input text "optcode"'
    # Execute the ADB command
    os.system(adb_command)
    return 'ADB command executed successfully!'

@app.route('/clear-app-data') # for loggin out of the app
def clear_app_data():
    # Replace with your actual app package name and main activity
    package_name = 'com.kwai.llcrm.del'
    main_activity = 'com.kwai.llcrm.del.MainActivity' # launcher activity
    # Clear app data
    clear_command = f'adb shell pm clear {package_name}'
    os.system(clear_command)
    # Relaunch the app
    launch_command = f'adb shell am start -n {package_name}/{main_activity}'
    os.system(launch_command)
    return 'App data cleared and app relaunched successfully!'


if __name__ == '__main__':
    app.run(port=5000)

