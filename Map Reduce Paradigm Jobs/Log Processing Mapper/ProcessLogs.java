package stubs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class ProcessLogs {

  public static void main(String[] args) throws Exception {

    if (args.length != 2) {
      System.out.printf("Usage: ProcessLogs <input dir> <output dir>\n");
      System.exit(-1);
    }

    Job job = new Job();
    job.setJarByClass(ProcessLogs.class);
    job.setJobName("Process Logs");

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setMapperClass(LogMonthMapper.class);
    job.setReducerClass(CountReducer.class);
    
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    /* 
     * Process Log drive by Dylan Skroskznik 
     * 
     * Creates reducer task size only fitting 12 for each month in a year 
     * partitioned obtained between this range respective to each month [0 , 11]
     * Uses 12 to apply output to a reducer
     */
    
    job.setNumReduceTasks(12);
    job.setPartitionerClass(MonthPartitioner.class);
    
    boolean success = job.waitForCompletion(true);
    System.exit(success ? 0 : 1);
  }
}
