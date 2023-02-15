package Services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    public static void fileReader(Path path) {
        if (Files.exists(path)) {
            try {
                byte[] bytes = Files.readAllBytes(path);
                String text = new String(bytes, StandardCharsets.UTF_8);

                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
