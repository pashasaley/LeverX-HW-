package task2;

import java.io.*;

public class Main {
    public static void main (String ... args) throws IOException {
        Work work = new Work();
        work.readLines();
        work.fix();
        work.toFile();
    }
}
