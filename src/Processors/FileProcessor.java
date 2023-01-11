package Processors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
    public ArrayList<String[]> scan() throws FileNotFoundException {
        ArrayList<String[]> lines = new ArrayList<String[]>();
        Scanner scanner = new Scanner(new File("src/Assets/rentals.csv"));

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();

            if(!currentLine.contains(",")) break;
            lines.add(currentLine.split(","));
        }

        return lines;
    }
}
