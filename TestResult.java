import java.util.ArrayList;
import java.util.List;

public class TestResult {
    private int numOfPass;
    private int numOfFailure;
    private List<Integer> failureId;
    private List<String> info;

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "numOfPass=" + numOfPass +
                ", numOfFailure=" + numOfFailure +
                ", failureId=" + failureId +
                '}';
    }

    public TestResult() {
        this.numOfPass = 0;
        this.numOfFailure = 0;
        this.failureId = new ArrayList<>();
        this.info = new ArrayList<>();
    }

    public int getNumOfPass() {
        return numOfPass;
    }

    public void setNumOfPass(int numOfPass) {
        this.numOfPass = numOfPass;
    }

    public int getNumOfFailure() {
        return numOfFailure;
    }

    public void setNumOfFailure(int numOfFailure) {
        this.numOfFailure = numOfFailure;
    }

    public List<Integer> getFailureId() {
        return failureId;
    }

    public void setFailureId(List<Integer> failureId) {
        this.failureId = failureId;
    }
}
