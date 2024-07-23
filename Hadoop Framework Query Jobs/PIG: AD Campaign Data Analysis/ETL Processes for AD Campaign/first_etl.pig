data = LOAD '/dualcore/ad_data1.txt' USING PigStorage() AS
	(keyword:chararray, campaign_id:chararray, data:chararray, time:chararray, display_site:chararray, 		was_clicked:int, cpc:int, country:chararray, placement:chararray);
fdata = FILTER data BY (country=='USA');
newdata = FOREACH fdata{ 
	GENERATE campaign_id,data,time,TRIM(UPPER(keyword)),display_site,placement,was_clicked,cpc;
}
STORE newdata INTO 'dualcore/ad_data1' USING PigStorage('\t');

