package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Example input line:
 * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
 *
 */
public class LogFileMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  
	  /*
	   * Contains each IP address in a array address from an 
	   * input string value
	   */
	  String[] address = value.toString().split(" ");
	  
	  if (address.length > 0) {
		  /* list the starting IP as key and an 1 as a value
		   * over each continuing value
		   */
	  
		  String x = address[0];
		  
		  context.write(new Text(x), new IntWritable(1));
		  
	  }
	  
  }
}