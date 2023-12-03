package exceptions;
import java.io.Closeable;
import java.io.IOException;

public class OCP_Exceptions01 {

/*
Exception in thread "main" java.lang.RuntimeException: Catch RT
	at exceptions.OCP_Exceptions.main(OCP_Exceptions.java:15)
 */


        public static void main(String[] args) throws Exception {
            // 1.  a new A() object instance is created within the Try-with-Resources block.
            try (A a1 = new A()) {
                /*
               2. new IOException() is explicitly thrown.
                After the IOException is thrown ,
                Automatic Resource Management calls close() from within the Try-with-Resources block
                which was called by the object instance A
                 */
                throw new IOException(); }

            //5. program flow control enters the catch block which catches the explicit IOException()
            catch(Exception e)
            {
                /*
                 within the catch block, a RuntimeException is explicitly thrown. T
                 his results in the original RuntimeException, getting suppressed i.e. it never gets executed.
                 */
                throw new RuntimeException("Catch RT");
                // "Exception in thread "main" java.lang.RuntimeException: Catch RT" gets printed  to the screen
            }
        }
}

class A implements Closeable {
    //3. the close() method is entered (from being called from the object instance A in the Try-with-Resources instance
    public void close() {
        //4. new RuntimeException exception is explicitly thrown.
        /*
        If a try block throws an exception and
        one or more exceptions are thrown by the try-with-resources,
        the exceptions thrown by try-with-resources are suppressed.
         */
        throw new RuntimeException("close() RT");
    }
}


