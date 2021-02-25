public class TestException extends Exception{
    private String message;

    public TestException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return " message='" + message + '\'' +
                '}';
    }
}
