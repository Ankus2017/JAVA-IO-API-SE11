package M04_Reader_Writer.src.org.paumard.reader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PayWithBufferedWriter04 {

    public static void main(String[] args) {

        //First pattern - creating the FileWriter in try with resources and passing it in to bufferedWriter
        //calling flush on bufferedWriter at the end
        try(FileWriter fileWriter = new FileWriter("files/words.txt")){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //it writes to its internal buffer (not to a file)
            bufferedWriter.write("Writing to buffered writer 1");

            //we need to call flush, to flush the internal buffer of bufferedWriter to FileWriter
            bufferedWriter.flush();

            //and when the close method is called the sentence will be sent to the file
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Second pattern - creating the FileWriter in try with resources and passing it in to bufferedWriter also created in try with resources
        //no need to call flush as it is called automatically when bufferedWriter is closed
        try(FileWriter fileWriter = new FileWriter("files/words.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){


            //it writes to its internal buffer (not to a file)
            bufferedWriter.write("Writing to buffered writer 2");


            //and when the close method is called the sentence will be sent to the file
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Third pattern - using the Files factory class from java.nio package, it takes path as a parameter
        Path path = Path.of("files/words.txt");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {

            bufferedWriter.write("Writing to a buffered writer 3");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
