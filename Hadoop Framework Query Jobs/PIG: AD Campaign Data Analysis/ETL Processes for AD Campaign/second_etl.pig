data = LOAD '/dualcore/ad_data2.txt' USING PigStorage(',') AS
--data = LOAD 'sample2.txt' USING PigStorage(',') AS
	(campaign_id:chararray, date:chararray, time:chararray, display_site:chararray, placement:chararray, 
	was_clicked:int, cpc:int, keyword:chararray);

unidata = DISTINCT data;
ndata = FOREACH unidata{
	GENERATE (campaign_id,REPLACE(date, '-', '/'),time,TRIM(UPPER	
	(keyword)),display_site,placement,was_clicked,cpc); 
}
--DUMP ndata;
STORE ndata INTO '/dualcore/ad_data2' USING PigStorage('\t');
