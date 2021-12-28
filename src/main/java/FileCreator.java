import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileCreator {

    /**
     * This method creates output file and update it with formatted text
     *
     * @param textToAddList
     * @throws IOException
     */
    public void createFile(List<String> textToAddList) throws IOException {
        FileWriter writer = new FileWriter("src/main/files/outputFile.txt");
        for (String string : textToAddList) {
            writer.write(string + System.lineSeparator());
        }
        writer.close();
    }
}
