package E_Learning.Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class CodeRunner {
    private static final String FILE_NAME = "Solution.java";
    private static final String TESTCASE_FILE = "testcases.txt";
    private static final String CLASS_NAME = "Solution";

    public static void main(String[] args) {
        String usercode0 = """
                public static int sum(int a, int b) {
                    return a + b ;
                }""";
        String userCode = """
            public class Solution {""" + usercode0 + """

                public static void main(String[] args) {
                    int a = Integer.parseInt(args[0]);
                    int b = Integer.parseInt(args[1]);
                    System.out.println(sum(a, b));
                }
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
        return process.waitFor() == 0;
    }

    public static void runTestCases() throws IOException {
        List<String> testCases = readTestCasesFromFile(TESTCASE_FILE);
        boolean testPassed = true;

        for (String testCase : testCases) {
            String[] parts = testCase.split(",");
            String[] inputs = Arrays.copyOfRange(parts, 0, parts.length - 1);
            String expectedResult = parts[parts.length - 1].trim().toString();

            String args = String.join(" ", inputs).trim();

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
            System.out.println("✅ All test cases passed!");
        } else {
            System.out.println("Some test cases failed. Please check the code.");
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
