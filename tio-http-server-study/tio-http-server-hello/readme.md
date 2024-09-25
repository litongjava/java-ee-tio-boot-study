```
root@ping-Inspiron-3458:~# ab -c1000 -n1000000 http://localhost/ok
This is ApacheBench, Version 2.3 <$Revision: 1843412 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100000 requests
Completed 200000 requests
Completed 300000 requests
Completed 400000 requests
Completed 500000 requests
Completed 600000 requests
Completed 700000 requests
Completed 800000 requests
Completed 900000 requests
Completed 1000000 requests
Finished 1000000 requests


Server Software:        -io
Server Hostname:        localhost
Server Port:            80

Document Path:          /ok
Document Length:        43 bytes

Concurrency Level:      1000
Time taken for tests:   122.359 seconds
Complete requests:      1000000
Failed requests:        0
Total transferred:      175000000 bytes
HTML transferred:       43000000 bytes
Requests per second:    8172.70 [#/sec] (mean)
Time per request:       122.359 [ms] (mean)
Time per request:       0.122 [ms] (mean, across all concurrent requests)
Transfer rate:          1396.70 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0   80 239.5     33    7339
Processing:     3   43  38.0     40    1720
Waiting:        0   31  37.5     28    1710
Total:          7  122 246.9     74    7383

Percentage of the requests served within a certain time (ms)
  50%     74
  66%     81
  75%     85
  80%     88
  90%     98
  95%    121
  98%   1096
  99%   1112
 100%   7383 (longest request)
```