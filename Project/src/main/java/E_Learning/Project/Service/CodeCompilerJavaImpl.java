package E_Learning.Project.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodeCompilerJavaImpl implements CodeCompiler {
    @Override
    public boolean compileJavaFile(String fileName) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("javac " + fileName);
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        boolean hasError = false;

        while ((line = errorReader.readLine()) != null) {
            System.out.println("Compilation Error: " + line);
            hasError = true;
        }

        return process.waitFor() == 0;
    }
}
