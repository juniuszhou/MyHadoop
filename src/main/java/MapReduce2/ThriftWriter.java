package MapReduce2;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import java.io.*;


/**
 * code from http://bit.ly/1Ylpa9A
 * Simple class that makes it easy to write Thrift objects to disk.
 *
 * @author Joel Meyer
 */
public class ThriftWriter {
    /**
     * File to write to.
     */
    protected final File file;

    /**
     * For writing to the file.
     */
    private BufferedOutputStream bufferedOut;

    /**
     * For binary serialization of objects.
     */
    private TBinaryProtocol binaryOut;

    /**
     * Constructor.
     */
    public ThriftWriter(File file) {
        this.file = file;
    }

    /**
     * Open the file for writing.
     */
    public void open() throws FileNotFoundException {
        bufferedOut = new BufferedOutputStream(new FileOutputStream(file), 2048);
        binaryOut = new TBinaryProtocol(new TIOStreamTransport(bufferedOut));
    }

    /**
     * Write the object to disk.
     */
    public void write(TBase t) throws IOException {
        try {
            t.write(binaryOut);
            bufferedOut.flush();
        } catch (TException e) {
            throw new IOException(e);
        }
    }

    /**
     * Close the file stream.
     */
    public void close() throws IOException {
        bufferedOut.close();
    }
}
