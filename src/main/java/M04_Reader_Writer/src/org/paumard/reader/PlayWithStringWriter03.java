package M04_Reader_Writer.src.org.paumard.reader;

import java.io.IOException;
import java.io.StringWriter;

public class PlayWithStringWriter03 {

    public static void main(String[] args) {

        try (StringWriter writer = new StringWriter();) {

            writer.write("Hello world!");

            /*
          Flushes the output stream and forces any buffered output bytes to be written out.
          The general contract of flush is that calling it is an indication that,
          if any bytes previously written have been buffered by the implementation of the output stream,
          such bytes should immediately be written to their intended destination.
             */
            //it flushes just before closing method which is called by try with resources pattern
           // writer.flush();

            StringBuffer buffer = writer.getBuffer(); //internal String Buffer
            System.out.println(buffer);
            String string = writer.toString();
            System.out.println(string);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
