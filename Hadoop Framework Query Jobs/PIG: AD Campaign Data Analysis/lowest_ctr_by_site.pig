-- Load only the ad_data1 and ad_data2 directories
data = LOAD '/dualcore/ad_data[12]' AS (
	     campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray,
             placement:chararray, was_clicked:int, cpc:int);

grouped = GROUP data BY display_site;

site = FOREACH grouped {
  -- Include only records where the ad was clicked
	clicked = FILTER data BY was_clicked == 1;
 -- count the number of records in this group
	numclick = COUNT(clicked);
	totaldata = COUNT(data);
  /* Calculate the click-through rate by dividing the 
   * clicked ads in this group by the total number of ads
   * in this group.
   */
	ctr = numclick*100.0/totaldata;
	GENERATE group, ctr AS ctr;
}

-- sort the records in ascending order of clickthrough rate
ord = ORDER site BY ctr ASC;
-- show just the first three
three = LIMIT ord 3;

--SAME CTR BUT FOR KEYWORDS WITH TOP CTR

grouped_key = GROUP data BY keyword;
key = FOREACH grouped_key {
	clicked = FILTER data BY was_clicked == 1;
	numclick = COUNT(clicked);
	totaldata = COUNT(data);
	ctr = numclick*100.0/totaldata;
	GENERATE group, ctr AS ctr;
}
ord_key = ORDER key BY ctr DESC;
three_key = LIMIT ord_key 3;

DUMP three;
DUMP three_key;
