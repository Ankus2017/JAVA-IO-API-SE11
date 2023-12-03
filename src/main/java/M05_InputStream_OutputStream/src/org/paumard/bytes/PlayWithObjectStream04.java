package M05_InputStream_OutputStream.src.org.paumard.bytes;



import M05_InputStream_OutputStream.src.org.paumard.bytes.model.User;

import java.io.*;

public class PlayWithObjectStream04 {

    public static void main(String[] args) {

        /*
        Serialization is the conversion of the state of an object into a byte stream; deserialization does the opposite.
        serialization is the conversion of a Java object into a static stream (sequence) of bytes,
        which we can then save to a database or transfer over a network.
         */

        User u1 = new User("Paul", 23);
        User u2 = new User("Jennifer", 25);

        try (FileOutputStream fos = new FileOutputStream("files/users.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            oos.writeObject(u1);
            oos.writeObject(u2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("files/users.bin");
             ObjectInputStream ois = new ObjectInputStream(fis);) {

            User paul = (User)ois.readObject();
            User jennifer = (User)ois.readObject();

            System.out.println("jennifer = " + jennifer);
            System.out.println("paul = " + paul);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
