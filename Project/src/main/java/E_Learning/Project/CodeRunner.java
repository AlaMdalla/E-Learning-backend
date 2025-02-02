package E_Learning.Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CodeRunner {
    //put this in a service
    private static final String FILE_NAME = "Solution.java";
    private static final String TESTCASE_FILE = "testcases.txt";
    private static final String CLASS_NAME = "Solution";
    private static  String pathToMain ="";
private  static String usercode0 =" "  ;
    public CodeRunner(String pathToMain,String usercode0 ) {
        this.pathToMain = pathToMain;
        System.out.println("pathto "+usercode0);
        this.usercode0=usercode0;
        this.main();
    }


    public static String readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        return Files.readString(path);

    }
    public  void main() {



        String   mainClass ="";
        try {
               mainClass = readFileContent(pathToMain);  // Specify the correct file path
        } catch (IOException e) {
            System.out.println("t3adechfile");
            e.printStackTrace();
        }        ;
        String userCode = """
            public class Solution {""" + usercode0 + mainClass+"""
            }
        """;

        try {
            saveCodeToFile(userCode);
            System.out.println("✅ Code saved to Solution.java.");

            if (!compileJavaFile()) {
                System.out.println("❌ Compilation failed.");
                return;
            } else {
                System.out.println("✅ Code compiled to Solution.java.");
            }

            runTestCases();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void saveCodeToFile(String code) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(code);
        }
    }

    public static boolean compileJavaFile() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("javac " + FILE_NAME);

        System.out.println(process.getErrorStream());
        return process.waitFor() == 0;
    }

    public static String runTestCases() throws IOException {
        List<String> testCases = readTestCasesFromFile(TESTCASE_FILE);
        boolean testPassed = true;

        for (String testCase : testCases) {
            String[] parts = testCase.split("\\|"); // Split by '|'

            if (parts.length < 2) {
                System.out.println("❌ Invalid test case format: " + testCase);
                continue;
            }

            String inputValues = parts[0].trim(); // Inputs
            String expectedResult = parts[1].trim(); // Expected output

            String[] inputArray = inputValues.split(","); // Split inputs by ','
            String args = String.join(" ", inputArray).trim(); // Convert to space-separated format

            Process process = Runtime.getRuntime().exec("java " + CLASS_NAME + " " + args);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = reader.readLine();
            reader.close();

            System.out.println("Test Case: " + args + " => Expected: " + expectedResult + " | Got: " + result);

            if (!result.equals(expectedResult)) {
                System.out.println("❌ Wrong answer for input: " + args);
                testPassed = false;
                break;
            }
        }

        if (testPassed) {
            return  "✅ All test cases passed!" ;
        } else {
            return "Some test cases failed. Please check the code.";
        }
    }

    public static List<String> readTestCasesFromFile(String fileName) throws IOException {
        List<String> testCases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                testCases.add(line);
                System.out.println(testCases);
            }
        }
        return testCases;
    }
}
