import sys
import json
import os.path as path
import time
from datetime import datetime, timezone

def main():
    saveFolder = ""
    if len(sys.argv) == 1:
        saveFolder = sys.argv[1]



def submit_req(startdate):
    monitor_url = 'http://54.191.3.235:80/v1/tripMonitor/check?startDate=' + startdate

    resp = requests.get(monitor_url)

    assert resp.status_code == 200

    result = resp.json()
    assert_result(result)

    return result


def assert_result(result):
    assert 'startDate' in result
    assert 'status' in result


