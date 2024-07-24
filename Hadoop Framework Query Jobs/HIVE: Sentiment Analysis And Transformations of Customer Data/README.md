# HIVE Sentiment Analysis/Data Transformation - Hadoop Framework Queries
---
### Overview
Introducing HIVE for this project, which aims to provide valuable insights into customer opinions, detect misinformation, and analyze cart abandonment behavior to help businesses improve customer satisfaction and reduce cart abandonment rates. This project will utilize Hive to perform general data analysis behind `/dualcore` customer service platform data such as key actions: 
- Analyze Numeric Ratings and Comments
- Create and Populate Weblog Tables
- Analyze Checkout Process
- Implement IP Geolocation
- Extract and Record Abandoned Product Data
- Estimate Shipping Costs

## Gaining The Insight From Sentimental Analysis
The portion applied Hive text processing to analyze customer product ratings and comments for `/dualcore`. We are aiming to detect misinformation and provide actionable insights for retailers and business consumers. Data is organized into three main tables: 
- Products Table:
  ```sql
      TABLE PRODUCTS
        productid, brand, name, price, cost, shipping price
  ```
- Customers Table:
  ```sql
      TABLE CUSTOMER
        customerid, first name, last name, address, city, state, zipcode
  ```
- Ratings Table:
  ```sql
      TABLE RATINGS
        timestamp, customerid, productid, rating, message string
  ```
- 
- The analysis involves two main steps:
  - Numeric ratings analysis identifies products with the highest and lowest average ratings, focusing on those with at least 50 ratings.
  - Next, rating comments analysis uses queries `SELECT FROM and LIKE` to examine customer messages for popular products.
  - Determine reasons for negative reviews by looking at specific details in the comments.
- This analysis provides insights into customer opinions and helps identify issues such as pricing or product quality.

[Gain Insight w/ Sentiment Analysis photos.pdf](https://github.com/user-attachments/files/16366180/Lab.A.Gain.Insight.w.Sentiment.Analysis.terminal.photos.pdf)



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
