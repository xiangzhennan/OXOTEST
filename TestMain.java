import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        TestResult testResult = new TestService().runTestService();
        TestResultWriter.outputReport(testResult);
    }
}
