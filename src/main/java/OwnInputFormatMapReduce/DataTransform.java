package OwnInputFormatMapReduce;

import MapReduce2.Calculator;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

import java.io.ByteArrayOutputStream;

public class DataTransform {



    public static byte[] MemberJoinToBytes(Calculator memberJoin) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
        TTransport transport = new TIOStreamTransport(outStream);
        TBinaryProtocol binaryOut = new TBinaryProtocol(transport);

        try {
            memberJoin.write(binaryOut);
            return outStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
