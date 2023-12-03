package M03_manipulating.src.org.paumard.files;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class PlayPath {

    public static void main(String[] args) {

            //interface from java.nio and it has diffrent implementations for diffrent file systems on Windows, linux or mac
            Path path1 = Paths.get("/images");

            //of method from Java 11
            Path path2 = Path.of("/videos");

            System.out.println("path1 = " + path1);
            System.out.println("path2 = " + path2);
            //class sun.nio.fs.UnixPath
            System.out.println("path1 = " + path1.getClass());

            // must start with 2 clashes or disk:/ on windows
            //on linux it just have to start with slash
            System.out.println("absolute " +path1.isAbsolute());
            System.out.println("root "+ path1.getRoot() );

            //resolve -> what will be the concatenation of path one and path 2, if path 2 is absolute it will just return path2
            Path resolve = path1.resolve(path2);
            System.out.println("resolved 1 + 2 " + resolve);

            //resolveSibling -> it resolves path2 against parent of path1
             Path resolveSibling = path1.resolve(path2);
            System.out.println("resolvedSibling 1 + 2 " + resolveSibling);

            //it will try to find the path that will take you from one directory to another
        // !!!!!!! Exception relativise can not work if one of the route is absolute
        /*
        Exception in thread "main" java.lang.IllegalArgumentException: 'other' is different type of Path
        at java.base/sun.nio.fs.UnixPath.relativize(UnixPath.java:398)
        at java.base/sun.nio.fs.UnixPath.relativize(UnixPath.java:43)
        at M03_manipulating.src.org.paumard.files.PlayPath.main(PlayPath.java:36)

         */
            Path relativize = path1.relativize(path2);

            System.out.println("relativize = " + relativize);

            //normalize -> simplifies the path
            Path path4 = Paths.get("/./data/videos");
        System.out.println("normalised " + path4.normalize());
        FileSystem fs = path4.getFileSystem();
        System.out.println("filesystem "+ fs);


        Path p1 =
                Paths.get("C:\\a\\","b\\File.txt");
        System.out.println(p1);

        /*-------------- On linux it works and prints file:/C:/a/File.txt */
        Path p2 =
                Paths.get(
                        "file:///C://a//File.txt");
        System.out.println("p2" +p2);

          /*
        Will throw InvalidPathException because "file:///C://a//" on Windows is a URL.

        Solution:

        Convert the string to Universal Resource Identifier (URI):
         making sure to import java.net.URI;

           */

        Path p3 =
        Paths.get( URI.create( "file:///C://a//File.txt"));
        System.out.println("p3 " +p3);  //    /C://a//File.txt


        Path p4 =
                Paths.get("C:\\a\\","b\\File.txt");
        System.out.println("p4 " +p4);


        Path p5 = Paths.get("\\a\\b\\c\\..");
        System.out.println("p5 " +p5.normalize()); // p5 \a\b\c\.. does not work cause we are on windows

        Path src = Paths.get("a/b/c/..");
        System.out.println("src " +src.normalize()); // src a/b



        Path p6 = Paths.get( "c:\\s\\d", "..\\..", "c:\\p\\q" ); //does not work cause we are on windows
        System.out.println( "p6 "+p6.normalize());

        Path p7 = Paths.get( "\\s\\d", "..\\..","\\p\\q" );
        System.out.println( "p7 "+p7.normalize());

        Path p8 = Paths.get( "c:\\s\\d", "..\\..","\\p\\q" );
        System.out.println("p8 "+ p8.normalize());


        Path p9 = Paths.get( "s/d", "../..","p/q" );
        System.out.println( "p9 "+p9.normalize());

        Path p10 = Paths.get( "/s/d", "../..","p/q" );
        System.out.println("p10 "+ p10.normalize());



        Path dir = Paths.get("c:\\a\\b\\");
        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(dir, "[AB]*")) {
            //for( Path p: stream) { //java: incompatible types: java.lang.Object cannot be converted to java.nio.file.Path
            for( Path p: stream) {
                System.out.println(p. getFileName());
            } }catch(Exception e) {
            System.out.println("problems locating directory"); }

        Path directory = Paths.get("files");
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
            for (Path file : directoryStream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException ex) {
            ex.printStackTrace();
        }

        //You use relativize() in constructing a path (bridge) from one location in the file system to another location
        Path project =
                Paths.get("files/an/na");
        Path file = Paths.get("files/ctest/dtest/log3.txt");
        System.out.println(" relativize " +project.relativize(file)); // relativize ../../ctest/dtest/log3.txt

        //To combine two paths together you use the
        //resolve() method
        Path abs = Paths.get("files/an/na");
        Path rel = Paths.get("ju/MyFile.txt");
        System.out.println("resolve " +abs.resolve(rel)); //resolve files/an/na/ju/MyFile.txt

        //PathMatcher  Must have two asterisks before .txt on DOS platform i.e."glob:**.txt"
        //
        //On unix platforms must be "glob:*.txt"
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        System.out.println("PathMatcher " +matcher.toString()); //PathMatcher sun.nio.fs.UnixFileSystem$3@5674cd4d

        PathMatcher pm = FileSystems.getDefault().getPathMatcher("regex:\\d{5}\\s.*");
        System.out.println("path matcher regex " +pm.matches(Paths.get("files12345")));

        String globPattern = "glob:*txt"; // "glob:**.{java,txt}" must contain any characters before '.java' or '.txt' e.g. C:\\a\\b\\z.java or C:\\a\\b\\s.txt.
        PathMatcher matcher2 = FileSystems.getDefault().getPathMatcher(globPattern);
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");
        boolean matched = matcher2.matches(path);
        System.out.format("%s matches  %s:  %b%n", globPattern, path, matched);

        Path directory2 = Paths.get("files"); //http://www.java2s.com/example/java-api/java/nio/file/pathmatcher/matches-1-0.html
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:sonnet?.txt");
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory2, "sonnet*.txt")) {
            for (Path file2 : directoryStream) {
                if (pathMatcher.matches(file2.getFileName())) {
                    System.out.println(file2.getFileName());
                }
            }
        } catch (IOException | DirectoryIteratorException ex) {
            ex.printStackTrace();
        }




    }
}
