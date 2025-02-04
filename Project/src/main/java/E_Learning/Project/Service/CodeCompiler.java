package E_Learning.Project.Service;
import java.io.IOException;


public interface CodeCompiler {
    boolean compileJavaFile(String fileName) throws IOException, InterruptedException;
}

