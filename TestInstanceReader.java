import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestInstanceReader {

    public static List<GameInstance> readTextAsObject() throws IOException {
        List<GameInstance> gameInstances = new ArrayList<>();
        //open folder,if you want use it locally, replace filename with your output path
        //you should be able to copy absolute path with a right mouse-click on your out folder in IDEA
        File testFolder = new File("D:\\Program Files\\onedrive\\OneDrive - University of Bristol\\Desktop\\java oop\\OXOtest\\testExamples");
        File[] files = testFolder.listFiles();
        if (files == null){
            throw new RuntimeException("directory is empty, you may want to check the name");
        }
        for (File file: files) {
            File[] testInstances = file.listFiles();
            if (testInstances == null){
                throw new RuntimeException("directory is empty, you may want to check the file structure");
            }
            for (File instance : testInstances) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(instance));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while((line = bufferedReader.readLine())!=null){
                    builder.append(line);
                    builder.append(" ");
                }
                gameInstances.add(StringToGameInstance(builder.toString()));
            }
        }
        return gameInstances;
    }

    private static GameInstance StringToGameInstance(String line) {
        String[] strings = line.split(" ");
        GameInstance gameInstance = new GameInstance();
        //according to format of txt, we get this magic number
        //first 6 strings are of some game setting info
        int startOfStep  = 6;
        //here comes the ugly part， use @builder from lombok will make it much better
        //but let's keep it this way, annotation is too advanced for now
        gameInstance.setGameId(Integer.decode(strings[0]));
        gameInstance.setNumOfRow(Integer.decode(strings[1]));
        gameInstance.setNumOfColumn(Integer.decode(strings[2]));
        gameInstance.setWinThreshold(Integer.decode(strings[3]));
        gameInstance.setNumOfPlayer(Integer.decode(strings[4]));
        gameInstance.setIndexOfWinner(Integer.decode(strings[5]));
        if (gameInstance.getIndexOfWinner() == -1){
            gameInstance.setExceptionName(strings[6]);
            startOfStep = 7;
        }
        gameInstance.setStepDetail(new ArrayList<>());
        List<String> stepDetail = gameInstance.getStepDetail();
        for (int i = startOfStep; i < strings.length; i++) {
            stepDetail.add(strings[i]);
        }
        return gameInstance;
    }
}
