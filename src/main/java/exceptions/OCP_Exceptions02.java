package exceptions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class OCP_Exceptions02 {

    public static void main(String[] args){
        try {
            if (args.length > 0) {
                throw new FileNotFoundException();
            } else {
                throw new SQLException();
            }
        } catch (FileNotFoundException | SQLException e) {
            System.out.println("exception thrown");
            //compiler error : java: multi-catch parameter e may not be assigned
            // e = new SQLException();}
        }

    }


}

class B{
    void mA1() throws SQLException, IOException { System.out.println("mA()");
        throw new IOException();
    }
    void mA2() /* throws SQLException, IOException*/ {
        try{
            mA1();
        }
        catch( IOException | SQLException e){
            System.out.println( "Exception");
         //   throw e;
            //java: unreported exception java.sql.SQLException; must be caught or declared to be thrown
        }
    }
    public static void main(String[] args) throws Exception {
        B b = new B();
        b.mA2();
        System.out.println("end");
    }
}

class C {

    public static void main(String[] args) throws Exception {
        try { ///access database/
            if (true) { throw new SQLException(); }
            else { throw new IOException(); } }
        catch (SQLException | IOException e)
        {
              /*
            Exception in thread "main" java.sql.SQLException
	at exceptions.C.main(OCP_Exceptions02.java:52)
             */
            System.out.println("before throw");
            throw e; //  Compile and rethrow e

            //java: unreachable statement compiler error
          //  System.out.println("after throw");

        }
    }
}

class D{
    public static void main(String[] args){
        int i = 1;
        try{
            throw new SQLException();
        }
        // Compiler error
        // java: exception java.io.FileNotFoundException is never thrown in body of corresponding try statement
        catch( /*FileNotFoundException | */SQLException e) {
            System.out.println( "exception thrown");
        } }
}

class E implements AutoCloseable{
    public void close() throws IOException {
        System.out.println("close() E");
        throw new IOException( "Closing"); }
}

class F {

    public static void main(String[] args) {
        try (E e = new E()) {
            throw new Exception("Try");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println(t);
            }
        }
    }

}