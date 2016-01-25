package SequenceFileOps;

/**
 * http://stackoverflow.com/questions/16070587/reading-and-writing-sequencefile-using-hadoop-2-0-apis
 File docDirectory = new File(docDirectoryPath);

 if (!docDirectory.isDirectory()) {
 System.out
 .println("Please provide an absolute path of a directory that contains the documents to be added to the sequence file");
 return;
 }

 /*
 * SequenceFile.Writer sequenceFileWriter =
 * SequenceFile.createWriter(fs, conf, new Path(sequenceFilePath),
 * Text.class, BytesWritable.class);

 // new api.
org.apache.hadoop.io.SequenceFile.Writer.Option filePath = SequenceFile.Writer
        .file(new Path(sequenceFilePath));
        org.apache.hadoop.io.SequenceFile.Writer.Option keyClass = SequenceFile.Writer
        .keyClass(Text.class);
        org.apache.hadoop.io.SequenceFile.Writer.Option valueClass = SequenceFile.Writer
        .valueClass(BytesWritable.class);

        SequenceFile.Writer sequenceFileWriter = SequenceFile.createWriter(
        conf, filePath, keyClass, valueClass);

        File[] documents = docDirectory.listFiles();

 try {
 for (File document : documents) {

 RandomAccessFile raf = new RandomAccessFile(document, "r");
 byte[] content = new byte[(int) raf.length()];

 raf.readFully(content);

 sequenceFileWriter.append(new Text(document.getName()),
 new BytesWritable(content));

 raf.close();
 }
 } finally {
 IOUtils.closeStream(sequenceFileWriter);
 }


 */

public class readWrite {
}
