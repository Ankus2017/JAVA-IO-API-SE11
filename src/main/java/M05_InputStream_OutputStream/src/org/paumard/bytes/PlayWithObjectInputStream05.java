package M05_InputStream_OutputStream.src.org.paumard.bytes;

import java.io.*;

public class PlayWithObjectInputStream05 {

    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream("files/users.bin");
             ObjectInputStream ois = new ObjectInputStream(fis);) {

            Object o1 = ois.readObject();
            Object o2 = ois.readObject();

            System.out.println("jennifer = " + o1);
            System.out.println("paul = " + o2);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
