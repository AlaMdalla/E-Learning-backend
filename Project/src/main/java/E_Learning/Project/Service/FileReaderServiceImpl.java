package E_Learning.Project.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public String readFileContent(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readString(path);
    }
}