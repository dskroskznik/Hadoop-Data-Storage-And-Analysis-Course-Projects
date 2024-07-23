-- Load only the ad_data1 and ad_data2 directories
data = LOAD '/dualcore/ad_data[1-2]/part*' AS (campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray,
             placement:chararray, was_clicked:int, cpc:int);

-- Include only records where the ad was clicked
-- Instead of looking for a possible click, we allow any possible click
-- as long as was_clicked will never be a null value
clicked = FILTER data BY was_clicked IS NOT NULL;
-- clicked = data.was_clicked
-- A: Group everything so we can call the aggregate function
grpd = GROUP clicked ALL;

-- B: Count the records 
total = FOREACH grpd GENERATE MAX(clicked.cpc)*50000;

-- C: Display the result to the screen
DUMP total;

