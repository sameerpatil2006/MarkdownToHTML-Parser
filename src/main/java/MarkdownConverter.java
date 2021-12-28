import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MarkdownConverter {

    /**
     * This is the main class, it extracts text from input file and pass to parser
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TextParser parseText = new TextParser();
        File file = new File("src/main/files/inputFile.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String string;
        while ((string = bufferedReader.readLine()) != null) {
            parseText.parseInputText(string);
        }
        parseText.addFormattedTextListToFile();
    }
}

