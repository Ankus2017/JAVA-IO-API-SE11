package M05_InputStream_OutputStream.src.org.paumard.bytes;

import java.io.*;

public class PlayWithDataStream03 {

    public static void main(String[] args) {

        int[] ints = {1, 2, 3, 4, 5};
        byte[] bytes = {};

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(os);) {

            for (int integer : ints) {
                dos.writeInt(integer);
            }
            dos.flush();
            bytes = os.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("bytes.length = " + bytes.length);

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             DataInputStream dis = new DataInputStream(bis);) {

            for (int i = 0; i < 5; i++) {
                int read = dis.readInt();
                System.out.println("read = " + read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
