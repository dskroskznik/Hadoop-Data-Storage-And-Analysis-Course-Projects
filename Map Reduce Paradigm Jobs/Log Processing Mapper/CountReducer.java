package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountReducer extends Reducer<Text, Text, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    
	  /* Count Reducer process by Dylan Skroskznik
	   */
	  int c = 0; 
	  
	  /* Count elements iterated using for loop and
	   * suppress any warnings that see unused elements 
	   * */
	  for(@SuppressWarnings("unused") Text e : values) { 
		  	c++; //Count increments
	  }
	  // Store unchanged text key and IntWritable contain the values count
	  context.write(key, new IntWritable(c));
  }
  
}
