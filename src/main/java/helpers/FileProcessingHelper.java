package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessingHelper {
    public List<String> readFromFile(String absoluteFilePath) throws FileNotFoundException {
        List<String> fileContents = new ArrayList<String>();
        File file = new File(absoluteFilePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            fileContents.add(scanner.nextLine());
        }

        return fileContents;
    }

    public String getAbsoluteFilePath(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("Invalid file path");
        }

        return file.getAbsolutePath();
    }
}
