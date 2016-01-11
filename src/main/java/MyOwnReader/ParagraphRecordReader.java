package MyOwnReader;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;


public class ParagraphRecordReader extends RecordReader<LongWritable, Text>
{
    private LongWritable lineKey;
    private Text lineValue;
    private int len = 0;
    public ParagraphRecordReader() throws IOException, InterruptedException {
    }

    @Override public void initialize(InputSplit var1, TaskAttemptContext var2)
            throws IOException, InterruptedException {

    }


    @Override public LongWritable getCurrentKey() throws IOException, InterruptedException {
        return new LongWritable(0);
    }

    @Override public Text getCurrentValue() throws IOException, InterruptedException {
        return new Text("0");
    }

    @Override public float getProgress() throws IOException, InterruptedException {
        return 0.0F;
    }

    @Override public void close() throws IOException {

    }

    @Override public boolean nextKeyValue() throws IOException, InterruptedException {
        len += 1;
        System.out.println("num "+len);
        return len < 10;
    }
}