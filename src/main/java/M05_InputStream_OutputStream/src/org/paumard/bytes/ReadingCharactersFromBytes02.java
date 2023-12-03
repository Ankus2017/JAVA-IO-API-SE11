package M05_InputStream_OutputStream.src.org.paumard.bytes;

import java.io.*;

public class ReadingCharactersFromBytes02 {

    public static void main(String[] args) {

        String hello = "Hello world!";

        ByteArrayOutputStream buffer = null;
        //first we create ByteArrayOutputStream and  then we pass it to OutputStreamWriter
        // who writes the characters  as bytes into buffer
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(baos)) {

            writer.write(hello);
            buffer = baos;

        } catch (IOException e) {
            e.printStackTrace();
        }

        //we get the bytes from the buffer  into byte array
        byte[] bytes = buffer.toByteArray();

        //then we pass the byte array to ByteArrayInputStream
        // which we pass into InputStreamReader
        // which we pass into bufferedReader
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             InputStreamReader isr = new InputStreamReader(bais);
             BufferedReader reader = new BufferedReader(isr);) {

            //and then we use the reader to read the characters
            String s = reader.readLine();
            System.out.println("s = " + s);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
