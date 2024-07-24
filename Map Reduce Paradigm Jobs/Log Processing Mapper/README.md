# Log Processing Mapper - Hadoop MapReduce Job
---
### Overview
The Writing Partitioner job is essential for detailed log analysis, particularly for organizing and analyzing web access logs by month. This is beneficial for network administrators and cybersecurity professionals to monitor trends and detect anomalies over time. This can perform processing web access log files, counting hits for each IP address, and distributing these counts across 12 reducers, one for each month. The Mapper extracts the IP address and month from each log entry, and the Partitioner directs the data to the appropriate reducer based on the month. The Reducer then counts the hits for each IP address per month. For example, it might log that a particular IP address accessed the server 50 times in January.

### Driver (ProcessLog.java):
The driver sets up the job by linking the Mapper and Reducer, configuring input/output paths, and defining the `LogMonthMapper.java` and `CountReducer.java` classes. It sets the job to split tasks into 12 reducers for each month and specifies output key and value types as `Text` and `IntegerWritable`.

### Mapper (LogMonthMapper.java):
The Mapper processes log entries to extract IP addresses and months. It converts input keys and values to `LongWritable` and `Text`, respectively, and uses a list array to reference months. The Mapper extracts the IP and month from log data, writing them as context key-value pairs.

### Partitioner (MonthPartitioner.java):
The Partitioner directs Mapper output to the appropriate reducer based on the month. It uses a `HashMap` to label months from 0 to 11 and implements methods to retrieve and assign month abbreviations to corresponding reducers. The number of reducers is set to 12 to match the months.

### Reducer (CountReducer.java)
The Reducer counts hits for each IP address per month. It iterates over values for each key, incrementing a count variable for each hit, and logs the counts into separate files for each month.

