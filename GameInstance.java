import java.util.List;

public class GameInstance {
    private int gameId;
    private int numOfRow;
    private int numOfColumn;
    private int winThreshold;
    private int numOfPlayer;
    private int indexOfWinner;
    private String exceptionName;
    private List<String> stepDetail;

    @Override
    public String toString() {
        return "GameInstance{" +
                "gameId=" + gameId +
                ", numOfRow=" + numOfRow +
                ", numOfColumn=" + numOfColumn +
                ", winThreshold=" + winThreshold +
                ", numOfPlayer=" + numOfPlayer +
                ", indexOfWinner=" + indexOfWinner +
                ", exceptionName='" + exceptionName + '\'' +
                '}';
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public int getIndexOfWinner() {
        return indexOfWinner;
    }

    public void setIndexOfWinner(int indexOfWinner) {
        this.indexOfWinner = indexOfWinner;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getNumOfRow() {
        return numOfRow;
    }

    public void setNumOfRow(int numOfRow) {
        this.numOfRow = numOfRow;
    }

    public int getNumOfColumn() {
        return numOfColumn;
    }

    public void setNumOfColumn(int numOfColumn) {
        this.numOfColumn = numOfColumn;
    }

    public int getWinThreshold() {
        return winThreshold;
    }

    public void setWinThreshold(int winThreshold) {
        this.winThreshold = winThreshold;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public List<String> getStepDetail() {
        return stepDetail;
    }

    public void setStepDetail(List<String> stepDetail) {
        this.stepDetail = stepDetail;
    }
}
