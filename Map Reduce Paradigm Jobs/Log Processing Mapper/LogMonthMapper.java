package stubs;

import java.util.List;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMonthMapper extends Mapper<LongWritable, Text, Text, Text> {

  /**
   * Example input line:
   * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
   *
   */
	
// Need list of strings holding months:
  public static List<String> month = Arrays.asList("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
	
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
	  /* Log Month Mapper by Dylan Skroskznik 10/8/2022    */

	 // Input string arr format split elem by space = " " 
	  String[] elem = value.toString().split(" ");
	  
	  if (elem.length > 3) {
		/* input stores addr to first element of string
		 * as long input is larger than 3 elem
		 **/
		  String ipaddr = elem[0];
		  
		  
		/* substring containing data & time are spaced by "/" 
		 * and stores month elem in index 2 while arr length is > 1
		 **/
		  String[] datetime = elem[3].split("/");
		  if (datetime.length > 1) {
			  String monthdati = datetime[1];
			  
		/* contents from output string check for valid month, context is written
		 **/
			  if (month.contains(monthdati)) {
				  context.write(new Text(ipaddr), new Text(monthdati));
			  }
				  
		  }
	  }
		  
	  
	  
  }
}
