package M03_manipulating.src.org.paumard.files;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import java.nio.file.SimpleFileVisitor;

public class PlayWithFiles {

    public static void main(String[] args) throws IOException {

        //File -> object from java.io it can be created independent from file system  File file1 = new File("path');
        //but it can also create and delete files (file.createNewFile()/directories (dir.mkdir(), dir.mkdirs() and analyse path
        //to copy or move files you need Files Factory class

        String current = System.getProperty("user.dir");
        System.out.println(current);

        //object from java.io
        File file1 = new File("test.txt");
        boolean exists = file1.exists();
        System.out.println(exists);
        boolean isFile = file1.isFile();
        System.out.println(isFile);
        boolean isDirectory = file1.isDirectory();
        System.out.println(isDirectory);

        File file2 = new File("./test.txt");
        boolean exists2 = file2.exists();
        System.out.println(exists2);
        boolean isFile2 = file2.isFile();
        System.out.println(isFile2);
        boolean isDirectory2 = file2.isDirectory();
        System.out.println(isDirectory2);

        File file3 = new File("files/sonnet3.txt");
        System.out.println(file3);
        boolean exists3 = file3.exists();
        System.out.println(exists3);
        boolean isFile3 = file3.isFile();
        System.out.println(isFile3);
        boolean isDirectory3 = file3.isDirectory();
        System.out.println(isDirectory3);

        //creating the file on the disk
        File file4 = new File("files/sonnet4.txt");
        boolean fileCreated = file4.createNewFile();
        System.out.println("fileCreated = " + fileCreated);
        boolean exists4 = file4.exists();
        System.out.println(exists4);


        File dir = new File("files/dir3/dir4");
        boolean dirCreated = dir.mkdir();
        System.out.println("dirCreated = " + dirCreated);
        boolean mkdirs = dir.mkdirs();
        System.out.println("mkdirs = " + mkdirs);


        boolean delete = dir.delete();
        System.out.println("delete = " + delete);



        dir = new File("images/mountain.jpg");
        String name = dir.getName();
        String parent = dir.getParent();
        String path = dir.getPath();

        System.out.println("name = " + name);
        System.out.println("parent = " + parent);
        System.out.println("path = " + path);

        //absolute: A pathname beginning with a single or more than two <slash> characters
        String absolutePath = dir.getAbsolutePath();

        //relative : A pathname not beginning with a <slash> character.

        /* canonical : system-dependent
        good way to define a canonical path will be: the shortest absolute path (short, in the meaning of string-length).

        This is an example of the difference between an absolute path and a canonical path:

        absolute path: C:\abc\..\abc\file.txt
        canonical path: C:\abc\file.txt
         */
        String canonicalPath = dir.getCanonicalPath();
        System.out.println("absolutePath = " + absolutePath);
        System.out.println("canonicalPath = " + canonicalPath);



        try{
            File a = new File("c:\\a");
            File f = new File(a,"f.txt");
            System.out.println(a);
            System.out.println(f);
            System.out.println("create f in a "+
                    f.createNewFile()); //java.io.IOException: No such file or directory
            //so it ends the try block -> the line after is not executed
            System.out.println(" exists f " +f.exists());
            // without try catch it throws an error and stops the execution so its not executed anyway

        }catch (Exception e){
            e.printStackTrace();
        }




        File f2 =
                new File("C:\\a\\b\\Log.txt" );
        System.out.println(" f2 create " +f2.createNewFile()); //if the path does not exist -> false

        File f3 =
                new File("./files/f3.txt" );

        //if the directory exists and file does not it creates the file and returns true
        System.out.println("f3 create " + f3.createNewFile());
        //if the file exists it returns false

        try {
            File f4 =
                    new File("./random/f3.txt");

            //if the directory does not exists it throws IOException
            System.out.println("f4 create " + f4.createNewFile());

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            File i = new File("C:\\atest\\btest\\");
            System.out.println(i);
            System.out.println("i.mkdir(); " +i.mkdir()); //false
            System.out.println(" i isDirectory "+
                    i.isDirectory()); // true ..cant find it
            System.out.println(" i exists "+
                    i.exists());  // true ..cant find it
            System.out.println(i.getAbsolutePath()); ///home/filemon/Documents/dev/IntellijProjects/Java/JAVA-IO-API-SE11/C:\atest\btest\

            ///////////////

            File i2 = new File("files/ctest/dtest");
            System.out.println(i);
            System.out.println(" i2.mkdir(); " + i2.mkdir()); //false
           // it did not create the files
            System.out.println(" i2 isDirectory "+
                    i2.isDirectory()); // false as i thought it should be in the example before...
            System.out.println(" i2 exists "+
                    i2.exists()); // false as i thought it should be in the example before...
            System.out.println(i2.getAbsolutePath());// /home/filemon/Documents/dev/IntellijProjects/Java/JAVA-IO-API-SE11/files/ctest/dtest

            ////////////
            File i3 = new File("files/ctest/dtest");
            System.out.println(i);
            System.out.println("i3.mkdirs() " +i3.mkdirs()); // it created both files !!!!
            System.out.println(" i3 isDirectory "+
                    i3.isDirectory()); // true
            System.out.println(" i3 exists "+
                    i3.exists()); // true
            System.out.println(i3.getAbsolutePath()); //  /home/filemon/Documents/dev/IntellijProjects/Java/JAVA-IO-API-SE11/files/ctest/dtest

            ///////
            File i4 = new File("C:\\atest\\btest\\");
            System.out.println(i4);
            System.out.println("i4.mkdirs() " +i4.mkdirs()); //false
            System.out.println(" i4 isDirectory "+
                    i4.isDirectory()); // true ..cant find it
            System.out.println(" i4 exists "+
                    i4.exists());  // true ..cant find it
            System.out.println(i4.getAbsolutePath()); ///home/filemon/Documents/dev/IntellijProjects/Java/JAVA-IO-API-SE11/C:\atest\btest\
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            //both files do exist
            Path src = Paths.get("files/ctest/dtest/log1.txt");
            Path des = Paths.get("files/ctest/dtest/log2.txt");

            Files.copy(src, des);
            //Because the log2.txt already exists it throws :
        /*
        Exception in thread "main" java.nio.file.FileAlreadyExistsException: files/ctest/dtest/log2.txt
        at java.base/sun.nio.fs.UnixCopyFile.copy(UnixCopyFile.java:573)
        at java.base/sun.nio.fs.UnixFileSystemProvider.copy(UnixFileSystemProvider.java:257)
        at java.base/java.nio.file.Files.copy(Files.java:1305)
        at M03_manipulating.src.org.paumard.files.PlayWithFiles.main(PlayWithFiles.java:182)
         */

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            //Log4.txt does not exist -> it succeeds
            Path src2 = Paths.get("files/ctest/log3.txt");
            Path des2 = Paths.get("files/ctest/log4.txt");

             Files.copy(src2, des2);

            File copied = new File("files/ctest/log4.txt");
            System.out.println(" copied exists "+copied.exists()); //true

        }catch (Exception e){
            e.printStackTrace();
        }


        Path p1 = Paths.get(URI.create( "file:///C://a//File.txt"));
        System.out.println("uri " +p1);

        File dir5 = new File("C:\\a\\b\\");
        System.out.println("dir5.mkdirs();" +dir5.mkdirs()); //false no directories created
        File f5=new File(dir5, "MyFile.txt");
        System.out.println("f5 "+ f5.createNewFile()); //true ?? object ? that is why its true ?

        File dir6 = new File("files/an/na/ju");
        System.out.println("dir6.mkdirs();" +dir6.mkdirs()); // true -> it worked
        File f6=new File(dir6, "MyFile.txt");
        System.out.println("f6 "+ f6.createNewFile()); //true


        File f7 =
                new File( "c:\\a\\b\\MyFile.txt");
        System.out.println("f7.exists() " +f7.exists()); //false
        System.out.println(" f7.createNewFile() "+
                f7.createNewFile() ); //true


       // File i8 =new File("files/test8"); //Exception in thread "main" java.io.IOException: No such file or directory
        File i8 =new File("files/ctest");
        File f8 =new File(i8,"log4.txt");

        System.out.println(f8.createNewFile());

        File i9 = new File("files/a/b");
        i9.mkdir();
        System.out.println(" i9 "+
                i9.isDirectory());

        File f9 =
                new File("files/an/na/ju/MyFile.txt");
        System.out.println("f9 can execute " +f9.canExecute() );
        System.out.println("f9 can write " +f9.canWrite() );
        System.out.println("f9 can read " +f9.canRead() );

        boolean f10 = Files.isExecutable(Paths.get("files/an/na/ju/MyFile.txt"));
        boolean f11 = Files.isWritable(Paths.get("files/an/na/ju/MyFile.txt"));
        boolean f12 = Files.isReadable(Paths.get("files/an/na/ju/MyFile.txt"));
        System.out.println("f10 isExecutable " +f10 );
        System.out.println("f11 isWritable " +f11 );
        System.out.println("f12 isReadable " +f12 );

        File f13 =
                new File("C:\\a\\b\\Log.txt");
        System.out.println("set last modified "  +
                f13.setLastModified(10) );

        Path p = Paths.get( "files/an/na/ju/MyFile.txt");
        BasicFileAttributes bfa = Files.readAttributes(p, BasicFileAttributes.class);
        System.out.println("'Basic file attributes' " + bfa.creationTime());


        Path dir7 = Paths.get("files");
        try (DirectoryStream stream = Files.newDirectoryStream(dir7)) {
            //for(Path path1: stream){ //java: incompatible types: java.lang.Object cannot be converted to java.nio.file.Path
               // for(Path path1: stream){ //Cannot convert type of expression path1 from java.nio.file.Path to java.lang.Object
            for(Object path1: stream){
              //  System.out.println(path1.getFileName());
                /*
                java: cannot find symbol
  symbol:   method getFileName()
  location: variable path1 of type java.lang.Object
                 */
            }
        } catch(Exception e) {
            System.out.println("problems locating directory"); }

        File f14 =
                new File("files/sonnet2.txt");
        System.out.println("sonnet2 last modified "  +
                f14.lastModified() );


    }
}








