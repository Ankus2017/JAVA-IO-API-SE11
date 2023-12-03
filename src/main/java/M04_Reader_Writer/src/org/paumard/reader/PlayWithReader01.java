package M04_Reader_Writer.src.org.paumard.reader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class PlayWithReader01 {

    public static void main(String[] args) {

        /*
        You can mark a stream of characters read by your reader and at some point go back to the mark you have put in the  reader
        markSupported() -> only in StringReader and CharArrayReader ( not supported in FileReader)
         */

        //Reader has extensions - FileReader, StringReader and CharArrayReader
        try (Reader reader = new FileReader("files/sonnet.txt")) {
        //try with resources pattern has been added in Java 7, exiting the try block will trigger the close method
            //Reader implements Closable which is an extension of AutoClosable

            char[] buf = new char[16];
            int read = reader.read(buf); //number of chars  that have been read on this pass
            StringBuilder sb = new StringBuilder();
            while (read > 0) {
                sb.append(buf, 0, read);
                read = reader.read(buf);
            }
            System.out.println("sb = \n" + sb);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //StringReader -> skip method
        String s = "Hello World";

        // create a new StringReader
        Reader reader = new StringReader(s);

        try {
            // read the first five chars
            for (int i = 0; i <=5; i++) {
                char c = (char) reader.read();

                // skip a char every time
                reader.skip(1);

                System.out.print("" + c);
            }

            // change line
            System.out.println();

            // close the stream
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
