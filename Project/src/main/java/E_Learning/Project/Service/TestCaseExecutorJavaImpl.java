package E_Learning.Project.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class TestCaseExecutorJavaImpl implements TestCaseExecutor {
    public String runTestCases(String testCaseFile, String className) throws IOException {
        List<String> testCases = readTestCasesFromFile(testCaseFile);
        boolean testPassed = true;

        for (String testCase : testCases) {
            String[] parts = testCase.split("\\|"); // Split by '|'

            if (parts.length < 2) {
                System.out.println("❌ Invalid test case format: " + testCase);
                continue;
            }

            String inputValues = parts[0].trim();
            String expectedResult = parts[1].trim();

            String[] inputArray = inputValues.split(",");
            String args = String.join(" ", inputArray).trim();

            Process process = Runtime.getRuntime().exec("java " + className + " " + args);

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
            return "✅ All test cases passed!";
        } else {
            return "Some test cases failed. Please check the code.";
        }
    }

    private List<String> readTestCasesFromFile(String fileName) throws IOException {
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
