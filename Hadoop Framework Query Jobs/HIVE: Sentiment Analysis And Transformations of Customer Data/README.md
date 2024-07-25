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
- The analysis involves two main steps:
  - Numeric ratings analysis identifies products with the highest and lowest average ratings, focusing on those with at least 50 ratings.
  - Next, rating comments analysis uses queries `SELECT FROM and LIKE` to examine customer messages for popular products.
  - Determine reasons for negative reviews by looking at specific details in the comments.
- This analysis provides insights into customer opinions and helps identify issues such as pricing or product quality.

![image](https://github.com/user-attachments/assets/41f6f910-6e59-4ebc-a17a-cd494f799cb9)
![image](https://github.com/user-attachments/assets/b92bb03e-f32b-41d9-a306-5ed5857afb6a)
![image](https://github.com/user-attachments/assets/f6b87be1-afe2-4d6d-8c01-a6df593aa9aa)
![image](https://github.com/user-attachments/assets/f5dc09e4-8e11-4fe1-9a62-c7cfbcf93e6b)





