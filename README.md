# Analytics-Trip-Monitor

This page details trip metrics monitor request api and return json format

## API
Now it only takes one parameter 'startDate', which is the date that you want to check trip metrics. The date is formated as yyyy-mm-dd. Note that due to trip metrics daily process nature, the available trip metrics has 48 hours delay, which means you should only query up to 2 days ago. Currently it does not have authentication.

### Request
    GET /v1/tripMonitor/check?startDate=2016-11-5


## Result Format
Return json has following components:

* *startDate:* The requested date.
* *status:* An indicator for health, will be 'OK', 'WARN', 'FAIL'.
* *details:* A list of validation detail.

Each items in details has following format:

* *rawProviderId:* providerId.
* *totalTripCount:* Total trip count for the request day.
* *previousAvgTripCount:* Historic average trip count.
* *thresholdType:* The threshold type, e.g Percentage, exact number, etc.
* *threshold:* e.g, if percentage, 20 as 20%.
* *reason:* human readable failure reason.

Example result:

    {
        "startDate": "2016-11-05",
        "status": "FAIL",
        "details": [
          {
            "rawProviderId": 83,
            "totalTripCount": 0,
            "previousAvgTripCount": 0,
            "thresholdType": null,
            "threshold": 0,
            "reason": "This provider id is missing from today's tripMetrics"
          },
          {
            "rawProviderId": 260,
            "totalTripCount": 49883,
            "previousAvgTripCount": 96220,
            "thresholdType": "PERCENT",
            "threshold": 0.2,
            "reason": "This provider's trip count exceeds moving average threshold"
          }
        ]
     }
