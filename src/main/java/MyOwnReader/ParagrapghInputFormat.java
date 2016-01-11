package MyOwnReader;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParagrapghInputFormat extends InputFormat<LongWritable, Text>
{
    @Override public List<InputSplit> getSplits(JobContext var1)
            throws IOException, InterruptedException {
        return new ArrayList<InputSplit>();
    }


    @Override public RecordReader<LongWritable, Text> createRecordReader(InputSplit var1, TaskAttemptContext var2)
            throws IOException, InterruptedException {
        return new ParagraphRecordReader();
    }
}