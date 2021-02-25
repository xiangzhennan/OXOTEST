import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestService {
    //player letters come from here
    private static final char[] playLetters ={'X','O','A','B','C'};

    public TestResult runTestService() throws IOException {
        TestResult testResult = new TestResult();
        ArrayList<GameInstance> gameInstances = (ArrayList<GameInstance>) TestInstanceReader.readTextAsObject();
        for (GameInstance gameInstance : gameInstances) {
            try{
                //initialise game
                OXOModel model = new OXOModel(gameInstance.getNumOfRow(), gameInstance.getNumOfColumn(), gameInstance.getWinThreshold());
                int numOfPlayer = gameInstance.getNumOfPlayer();
                for (int i = 0; i < numOfPlayer; i++) {
                    model.addPlayer(new OXOPlayer(playLetters[i]));
                }
                OXOController controller = new OXOController(model);
                List<String> stepDetail = gameInstance.getStepDetail();
                //start place chess
                for (int i = 0; i < stepDetail.size(); i++) {
                    checkCurrentPlayer(model,gameInstance,i);
                    //execute command
                    controller.handleIncomingCommand(stepDetail.get(i));
                    checkEarlyEndGame(stepDetail,model,i);
                }
                //when game end, check game drawn or winner correctness
                if (gameInstance.getIndexOfWinner() == -2){
                    checkDrawn(model,testResult);
                }else {
                    checkWinner(gameInstance,model,testResult);
                }
            }catch (Exception e){
                //if exception happens, check if it is the right exception type
                if (gameInstance.getIndexOfWinner() == -1){
                    //remove package name
                    String[] split = e.getClass().getName().split("\\.");
                    if (gameInstance.getExceptionName().equals(split[split.length-1])){
                        testResult.setNumOfPass(testResult.getNumOfPass()+1);
                    }else{
                        String message = getStackMessageAsString(e);
                        e = new TestException("expect Exception "+ gameInstance.getExceptionName()
                                +" but get "+ split[split.length-1] +"\n"+message);
                        testFailureProcess(testResult,gameInstance,e);
                    }
                }else{
                    e = new TestException("expect no exception with winner to be '"+
                            getWinnerMessage(gameInstance.getIndexOfWinner()) +
                            "' but get "+ e.getClass().getName() +"\n"+getStackMessageAsString(e));
                    testFailureProcess(testResult,gameInstance,e);
                }
            }
        }
        return testResult;
    }

    private String getWinnerMessage(int indexOfWinner) {
        if (indexOfWinner >= 0){
            return String.valueOf(playLetters[indexOfWinner]);
        }else {
            return "gamedrawn";
        }
    }

    private String getStackMessageAsString(Exception e) {
        StringBuilder builder = new StringBuilder();
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement element :stackTrace ) {
            builder.append(element.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    private void checkDrawn(OXOModel model, TestResult testResult) throws TestException {
        if (model.isGameDrawn()){
            testResult.setNumOfPass(testResult.getNumOfPass()+1);
        }else{
            throw new TestException("game should be drawn but not");
        }
    }

    private void checkWinner(GameInstance gameInstance, OXOModel model,TestResult testResult) throws TestException {
        if (playLetters[gameInstance.getIndexOfWinner()]!=(model.getWinner().getPlayingLetter())){
            //game end but with wrong winner or no winner
            throw new TestException("game end but with wrong winner or no winner, wrong winner: "+model.getWinner());
        }else{
            testResult.setNumOfPass(testResult.getNumOfPass()+1);
        }
    }

    private void checkEarlyEndGame(List<String> stepDetail,OXOModel model,int stepNum) throws TestException {
        if (stepNum != stepDetail.size() - 1&&(model.getWinner()!=null||model.isGameDrawn())){
            //game end earlier than it should
            throw new TestException("game end earlier than it should at step "+stepNum);
        }
    }

    private void checkCurrentPlayer(OXOModel model, GameInstance gameInstance, int stepNum) throws TestException {
        if (model.getCurrentPlayer().getPlayingLetter()!= playLetters[stepNum%gameInstance.getNumOfPlayer()]){
            //wrong current player
            throw new TestException("wrong current player at step "+ stepNum);
        }
    }
    //change info in testResult
    public void testFailureProcess(TestResult testResult, GameInstance gameInstance, Exception e){
        testResult.setNumOfFailure(testResult.getNumOfFailure()+1);
        testResult.getFailureId().add(gameInstance.getGameId());
        testResult.getInfo().add("example "+ gameInstance.getGameId()+" fail:"+e.toString());

    }
}
