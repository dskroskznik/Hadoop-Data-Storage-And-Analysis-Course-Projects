-- TODO (A): Replace 'FIXME' to load the test_ad_data.txt file.

--data = LOAD 'test_ad_data.txt' USING PigStorage(',') AS (campaign_id:chararray,
data = LOAD '/dualcore/ad_data[1-2]/part*' AS (
	campaign_id:chararray, date:chararray, time:chararray, keyword:chararray, display_site:chararray, 
	placement:chararray, was_clicked:int, cpc:int);


-- TODO (B): Include only records where was_clicked has a value of 1
click1 = FILTER data BY was_clicked == 1;


-- TODO (C): Group the data by the appropriate field
grpsite = GROUP click1 BY keyword;


/* TODO (D): Create a new relation which includes only the 
 *           display site and the total cost of all clicks 
 *           on that site
 */
totalcostg = FOREACH grpsite GENERATE group AS display_site, SUM(click1.cpc) AS total;

-- TODO (E): Sort that new relation by cost (ascending)
totalcostg = ORDER totalcostg BY total DESC;

-- TODO (F): Display just the first three records to the screen
top5disp = LIMIT totalcostg 5;
DUMP top5disp;
