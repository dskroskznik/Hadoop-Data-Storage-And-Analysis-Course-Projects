# Log Month Partitioner - Hadoop MapReduce Job
---
## Overview
The Log File Analysis MapReduce job is crucial for web server management and cybersecurity. It helps monitor and analyze web traffic, detect suspicious activity, and identify potential security threats. This analysis can guide network administrators to take preventative measures against attacks and optimize server performance. This MapReduce Job can analyze web server log files to count the times each IP address accesses the server. It maps each IP address to a count of 1 for each hit and then reduces these counts to get the total number of hits per IP address. This helps identify the most frequent visitors and potentially malicious IPs. For example, it can reveal that a specific IP address accessed the server 150 times, indicating a possible security threat.

### Driver (ProcessLogs.java):
The driver configures the log analysis job, setting input and output paths, and specifying classes for the Mapper and Reducer. It sets the key as `Text` and the value as `IntWritable` to tally IP address hits.

### Mapper (LogFileMapper.java):
The Mapper organizes log file data, mapping each IP address as the key and counting hits with a value of 1. It processes IP addresses into a string array, filtering valid addresses.

### Reducer (SumReducer.java):
The Reducer receives keys (IP addresses) and their hit counts. It sums the hits for each IP address and outputs the IP address with the total count as `IntWritable`.

### Dataflow:
The job maps IP addresses from log files, counting occurrences in the Mapper. The Reducer sums these counts, outputting the total hits for each IP address. This helps identify and analyze frequently hit IP addresses for security purposes.

