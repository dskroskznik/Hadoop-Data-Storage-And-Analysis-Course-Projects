# PIG Analyzing AD Campaign Data - Hadoop Framework Queries
---
### Overview
Introducing Apache Pig for Extract, Transform, and Load (ETL) processing, utilized to analyze ad campaign data. The primary goal is to practice data investigation and reorganization, leveraging Pig's scripting capabilities to handle large datasets efficiently in Hadoop Distributed File System (HDFS). We will be demonstrating the Apache Pig big data analysis through a `/dualcore` Hadoop database provided. The key importance of this Apache Pig exercise is to understand and value the following: 
- Scalability with processing large datasets efficiently.
- Automating ETL processes with Pig reduces manual effort and improves data processing speed and accuracy.
- Data analysis that provides insights into ad campaign performance, helping businesses optimize their advertising strategies.

## Using Extract, Transform, Load (ETL) Processing (first_etl.pig, second_etl.pig):
Scripts here were tested locally before being applied to the HDFS. The input data files are placed in the `/dualcore` directory. The output is stored in a tab-delimited format in `/dualcore`.
For the **First ETL Script** we run `first_etl.pig` against the ad campaign samples by:
1. Filtering Data: Load the input data from "sample.txt" and filter entries where the country field is 'USA'.
2. Reformatting Fields: Generate a new dataset excluding the 'country' field, remaining are as:
   ```({keyword}, {campaign_id}, {date}, {time}, {display_site}, {placement}, {was_clicked}, {cpc})```
3. Keyword Modification: Convert the 'keyword' field to uppercase and trim any whitespace.
![A3 1](https://github.com/user-attachments/assets/08ab64d3-ff0c-461f-86ba-cedb23f181f4)
![A3 2](https://github.com/user-attachments/assets/f97bd5fb-8080-4e43-ba08-5fc4e4ca4081)


Next, we activate the **Second ETL Script** and run `second_etl.pig` against the next set of ad campaign samples, which does:
1. Loading and Deduplication: Load input data and remove duplicate records using the `DISTINCT` command.
2. Field Reordering: Reformat the fields in the following order:
  ```({keyword}, {campaign_id}, {date}, {time}, {display_site}, {placement}, {was_clicked}, {cpc})```
3. Keyword and Date Modification: Convert `{keyword}` to uppercase and trim whitespace. Replace '-' with '/' in the `{date}` field.
![A3 3](https://github.com/user-attachments/assets/5c4b487b-6dbd-4283-b14b-b7d83d3b376f)

## 
