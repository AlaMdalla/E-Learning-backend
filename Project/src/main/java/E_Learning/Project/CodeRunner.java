package E_Learning.Project;

import E_Learning.Project.Service.CodeCompiler;
import E_Learning.Project.Service.FileReaderService;
import E_Learning.Project.Service.TestCaseExecutor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CodeRunner {

    private final FileReaderService fileReaderService;
    private final CodeCompiler codeCompiler;
    private final TestCaseExecutor testCaseExecutor;
public  String result ="";
    private static final String FILE_NAME = "Solution.java";
    private static final String TESTCASE_FILE = "testcases.txt";
    private static final String CLASS_NAME = "Solution";

    public CodeRunner(FileReaderService fileReaderService, CodeCompiler codeCompiler, TestCaseExecutor testCaseExecutor) {
        this.fileReaderService = fileReaderService;
        this.codeCompiler = codeCompiler;
        this.testCaseExecutor = testCaseExecutor;
    }

    public void run(String pathToMain, String usercode0) {
        String mainClass = "";
        try {
            mainClass = fileReaderService.readFileContent(pathToMain);
        } catch (IOException e) {
            System.out.println("File reading failed.");
            e.printStackTrace();
        }

        String userCode = """
                import java.util.*;
                public class Solution {""" + usercode0 + mainClass + """
                }
        """;

        try {
            saveCodeToFile(userCode);
            System.out.println("✅ Code saved to Solution.java.");

            if (!codeCompiler.compileJavaFile(FILE_NAME)) {
                System.out.println("❌ Compilation failed.");
                return;
            } else {
                System.out.println("✅ Code compiled.");
            }

             result = testCaseExecutor.runTestCases(TESTCASE_FILE, CLASS_NAME);
            System.out.println(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveCodeToFile(String code) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(code);
        }
    }
}

