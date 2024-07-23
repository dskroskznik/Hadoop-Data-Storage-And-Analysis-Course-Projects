package stubs;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class ProcessLogs {

  public static void main(String[] args) throws Exception {

    if (args.length != 2) {
      System.out.printf("Usage: ProcessLogs <input dir> <output dir>\n");
      System.exit(-1);
    }

    Job job = new Job();
    
    /*
     * Set our primary jar class for ProcessLogs
     *  & the job name Process Logs fo this job
     */
    job.setJarByClass(ProcessLogs.class);
    job.setJobName("Process Logs");

    /*
     * Set together the path arguments for both
     *  our in and output paths
     */
    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    	
    /*
     * Call and set each separate Mapper & Reducer class
     *  & and assign them for this driver
     */
    job.setMapperClass(LogFileMapper.class);
    job.setReducerClass(SumReducer.class);
    
    /*
     * Supply each output specific format class
     * to our output key, and value class
     */
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    boolean success = job.waitForCompletion(true);
    System.exit(success ? 0 : 1);
  }
}
