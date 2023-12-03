package M03_manipulating.src.org.paumard.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/*
You have implemented your own versions of all methods in SimpleFileVisitor class.
Inside the main class you have: .... Files.walkFileTree(...).

Explain the sequence of method calls
#1 preVisitDirectory() goes through each folder
#2 visitFile() method checks for any files inside the current folder The above method pair continue through searching for all folders and subfolders
#3 Having reached the deepest folder:
#4 postVisitDirectory() goes back up the folder tree
any empty folders on return search are found with preVisitDirectory() method call
 */

//https://www.geeksforgeeks.org/java-nio-file-simplefilevisitor-class-in-java/
public class PlayWithFileVisitor  extends SimpleFileVisitor<Path> {
    public FileVisitResult visitFile(Path file, BasicFileAttributes atts) throws IOException
    {
        System.out.println(file.getFileName());
        if (file.getFileName().toString().endsWith(
                ".xt")) { // delete files ending with .txt
            Files.delete(file);
        } // return result of the operation
        return FileVisitResult.CONTINUE;
    }
    public static void main(String args[]) throws Exception
    {
        PlayWithFileVisitor visitor = new PlayWithFileVisitor();
        try {
            // visiting all files at
            // "/Users/abhinavjain194/desktop/visit"
            Files.walkFileTree(
                    Paths.get(
                            "files"),
                    visitor);
        }
        catch (Exception e) { // printing error if occurred
            System.err.print(e.toString());
        }
    }
}
class A extends SimpleFileVisitor<Path> //need to extend SimpleFileVisitor<Path>
{
    // @Override //java: method does not override or implement a method from a supertype
    public FileVisitResult visitFile(
            Path file, BasicFileAttributes at) throws IOException
    {
        System.out.println(file.getFileName());
        if(file.getFileName().toString().endsWith(".t") ){
            Files.delete(file);
        }
        return FileVisitResult.CONTINUE;
    }
    public static void main(String[] args) throws Exception {
        A dirs = new A();
        Files.walkFileTree(Paths.get("files"), dirs);
    }
}


