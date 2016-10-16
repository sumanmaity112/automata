package parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class InputReader {
    public static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        return new String(data, "UTF-8");
    }
}
