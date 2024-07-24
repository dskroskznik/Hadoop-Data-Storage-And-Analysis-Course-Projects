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
2. Reformatting Fields: Generate a new dataset excluding the 'country' field, remaining are as (cpc = cost per click):
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

## Analyzing Ad Campaign Data with Apache Pig
Let's now establish what we processed with the ETL transfer to import data using Sqoop and MySQL. Here are a handful of analytical scripts run measuring the outcome and interactive numbers of this ad campaign. 
A subset of 100 data entries is formatted for analysis, specifically organized similarly as:
```(campaign_id, date, time, keyword, display_site, placement, was_clicked, cpc)```

**Low-Cost Sites** (low_cost_sites.pig):
This script filters records where `was_clicked` equals 1, and groups together `display_site` and cost per click or `cpc` values
Then sorts the results in **ascending** order of cost and displays the top **three** lowest-cost sites.

![A3 4](https://github.com/user-attachments/assets/739794c9-276f-4047-a5da-63a29f465bc0)

**High-Cost Sites** (high_cost_keywords.pig):
This script filters records where `was_clicked` equals 1, groups by website `keyword` and the sum of `cpc` values.
Next, sort the results in **descending** order of cost and display the top **five** highest-cost sites.

![A3 5](https://github.com/user-attachments/assets/47a13eb3-62e5-4525-9ffb-fe40e8ab681f)

**Total Website Clicks Calculation** (total_click_count.pig):
This script gathers records where `was_clicked` equals 1 as the base, next groups all records and counts the total number of clicks to the websites.

![A3 6](https://github.com/user-attachments/assets/434035bf-fce1-4b92-9159-1159cc8e7ffb)

**Campaign Costs of Project Next** (project_next_campaign_cost.pig):
This script filters records where `was_clicked` is **not NULL**, next is grouping all records and finding the maximum `cpc`. 
Next, we will multiply by 50,000 to estimate the **worst-case** scenario.

![A3 7](https://github.com/user-attachments/assets/42c8d2eb-febf-4e75-b94c-39c3449a390e)

**Calculate Click-Through Rate or CTR By Site** (lowest_ctr_by_site.pig):
This script will be grouped by `display_site` and then filter clicked records. From this, we will count the total clicks on record and the total amount of records.
For viewing the `click-through rate`, the script will calculate the `click-through rate` or CTR and sort sites by CTR in **ascending** order to display the **lowest** three. 
We can then repeat the process through **highest** CTR by `keyword` and display the top five.

![A3 8](https://github.com/user-attachments/assets/8d1214ea-95cb-4395-84b6-8825f4b1ebb2)
