import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestResultWriter {
    public static void  outputReport(TestResult testResult) throws IOException {
        File file = new File("out/production/OXOtest/test_report.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("pass test examples: "+ testResult.getNumOfPass()+
                " out of " + (testResult.getNumOfPass()+testResult.getNumOfFailure()));
        writer.write("\n");
        for (String s : testResult.getInfo()) {
            writer.write(s);
            writer.write("\n");
            writer.write("------------------------------------");
            writer.write("\n");
        }
        writer.close();
    }

}
