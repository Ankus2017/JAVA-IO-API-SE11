package M06_Console.src.org.paumard.console;

import java.io.Console;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayWithConsole {

    public static void main(String[] args) {

        Console console = System.console();

        PrintWriter writer = console.writer();
        writer.printf("What is your name? > ");

        String name = console.readLine();
        writer.printf("Hello %s\n", name);
        writer.printf("What is your password %s? ", name);

        char[] chars = console.readPassword();
        String password = new String(chars);
        writer.printf("%s is an easy password to guess!\n", password);
        writer.printf("What are your favorite colors %s? ", name);
        String colors = console.readLine();

        List<String> providedColors =
                new Scanner(colors)
                    .useDelimiter(" ")
                    .tokens()
                    .collect(Collectors.toList());
        writer.println(providedColors);

        String n =
                console.readLine("%s", "Enter Name: ");
        System.out.println(
                "Welcome "+n);

    }
}
