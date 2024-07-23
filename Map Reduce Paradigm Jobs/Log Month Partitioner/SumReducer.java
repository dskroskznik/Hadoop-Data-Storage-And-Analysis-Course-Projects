package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {

	    /*
	     * SUM REDUCER Process by Dylan Skroskznik
	     */
		  
		  int wordCounts = 0;
		  
		/* for loop counts out each address from value get
		 * */  
		  for (IntWritable ipValues : values) {
			  wordCounts += ipValues.get();
			  
		  }
		  
		  context.write(key, new IntWritable(wordCounts));
	  
  }
}