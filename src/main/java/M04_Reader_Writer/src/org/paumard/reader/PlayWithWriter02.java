package M04_Reader_Writer.src.org.paumard.reader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class PlayWithWriter02 {

    public static void main(String[] args) {

        /*
        Writer has three extensions FileWriter, StringWriter, CharArrayWriter
         */
        try (Writer writer = new FileWriter("files/words.txt")) {

            // closing w writer automatically calls flush method ( you do not need to call it explicitly)

            writer.write("Hello world!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
