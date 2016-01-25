package MapfileAsOutput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;


public class NewWordCount {

    public static class TokenizerMapper extends Mapper<Object, Text, Text, LongWritable>{

        private final static LongWritable one = new LongWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());

                context.write(word, one);
            }
        }
    }

    // put output in the same directory and several partition files.
    public static class IntSumReducer
            extends Reducer<Text,LongWritable,Text,LongWritable> {
        private LongWritable result = new LongWritable();

        public void reduce(Text key, Iterable<LongWritable> values,
                           Context context
        ) throws IOException, InterruptedException {

            System.out.println("junius into reducer");
            try {
            int sum = 0;
            for (LongWritable val : values) {
                System.out.println("key is " + key + " " + val.get());
                sum += val.get();
            }
            result.set(sum);

            context.write(key, result); }
            catch (Exception e) {
                //context.
                e.printStackTrace();
            }
            System.out.println("junius outside reducer");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("new version 2");
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        // set classed defined in this app.
        job.setJarByClass(NewWordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);


        //set output key value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        //job.setMapOutputKeyClass(Text.class);
        //job.setMapOutputValueClass(LongWritable.class);

        // if we set more than one reduce. then output also will be partitioned
        // to different part-r- folders.
        job.setNumReduceTasks(4);


        //set input output path.
        String input = args[0];
        String output = args[1];

        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        // set out put file format.
        //job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(MapFileOutputFormat.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}


