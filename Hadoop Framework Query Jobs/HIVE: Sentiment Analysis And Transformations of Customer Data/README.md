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

## Data Transformations with Hive 
This portion of the project uses Hive to focus on analyzing customer behavior, particularly cart abandonment during the checkout process. Data formatting will identify areas of analysis involving constructing weblogs with HIVE Hql and RegexSerDe to parse product log files, verifying selecting terms, counting each term, and checking inventory amounts. Some checkout analysis logs data for each step of the checkout process and displays where customers abandon their carts. IP geolocation transforms IP addresses, website cookies, and checkout steps into Python scripts with ZIP codes and creates a table based on cart ZIP codes for geolocation. Shipping costs are estimated by writing a cart items table to record items from each cart, and joining ZIP codes and product data to estimate costs using Hive UDFs.and steps include. The following are the important steps towards this process: 
  1. Creating weblog tables using `RegexSerDe` to parse lines from the `access.log` file in HDFS
     - Table populated with popular items from user searches on the **Dualcore Website**.
  2. Customer checkout analysis involves detecting shopping cart additions and checkouts.
     - Logging data each step in the checkout process.
  3. IP geolocation is implemented using a `TRANSFORM` Python script to convert checkout sessions to ZIP codes.
     - The geolocation chart is created based on cart ZIP codes.
  4. Abandoned product data is extracted using the `REGEXP EXTRACT` function to locate product IDs.
     - Tables are created to record product data from a joint weblogs table.
  5. Shipping costs are calculated using **User Defined Functions (UDF)** to estimate costs from given ZIP codes and item weights.
     - The `cart_orders` table is created to sum up all shipping, price, and wholesale costs.

![image](https://github.com/user-attachments/assets/3145d9a7-fef7-488c-85f7-a0a7f03a62d6)
![image](https://github.com/user-attachments/assets/f1985650-8c78-491f-a8ef-9af41b0ca16f)
![image](https://github.com/user-attachments/assets/501109d1-21ab-4e5e-b1d6-b036ffd66a55)
![image](https://github.com/user-attachments/assets/935a0ee9-b7b9-4fcd-ab1d-2913f18da558)
![image](https://github.com/user-attachments/assets/e12e2a6c-fc23-4ac1-a7bc-09d4cfe73909)
![image](https://github.com/user-attachments/assets/7f6683a5-6720-48b9-b7cb-8656a67640ae)
![image](https://github.com/user-attachments/assets/2e8ef6d0-a431-44d1-9afc-1d3073d53bd3)
![image](https://github.com/user-attachments/assets/854fa061-b3a8-4c36-8c27-1cff0779c3a5)
![image](https://github.com/user-attachments/assets/55c1f32c-c074-468e-8c7a-82ae1b292b8d)


  


