package M04_Reader_Writer.src.org.paumard.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PayWithBufferedReader05 {

    public static void main(String[] args) {

        /*
        Buffers are defined inside java. It is the block of memory into which we can write data, which we can later be read again.
        The memory block is wrapped with a NIO buffer object, which provides easier methods to work with the memory block.

        //decorator Pattern
        BufferedReader is both an extension and composition of the Reader class
        BufferedReader class extends the abstract reader class and to create a BufferedReader,
        you need to provide a concrete extension of this reader abstract class ex. FileReader
         */

        //reading the file line by line
        Path path = Path.of("files/sonnet.txt");
        try (BufferedReader bufferedReader = Files.newBufferedReader(path);) {

            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println("line = " + line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Using Stream API from JAVA 8 that deals with map/filter/reduce pattern
        try (BufferedReader bufferedReader = Files.newBufferedReader(path);) {

            //lines() method returns a stream
            Stream<String> lines = bufferedReader.lines();
            lines.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
