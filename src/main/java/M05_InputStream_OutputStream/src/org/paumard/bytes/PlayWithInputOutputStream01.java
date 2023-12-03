package M05_InputStream_OutputStream.src.org.paumard.bytes;

import java.io.*;

public class PlayWithInputOutputStream01 {

    public static void main(String[] args) {

        byte [] header = {0xC, 0xA, 0xF, 0xE, 0xB, 0xA, 0xB, 0xE};

        try (OutputStream os = new FileOutputStream("files/data.bin")) {

            os.write(header);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream is = new FileInputStream("files/data.bin")) {

            byte[] allBytes = is.readAllBytes();
            for (byte allByte : allBytes) {
                System.out.printf("0x%x ", allByte);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}