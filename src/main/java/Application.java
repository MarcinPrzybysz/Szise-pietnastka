import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        String strategy = args[0];
        String strategyParam = args[1];
        String inputFile = args[2];
        String solutionFile = args[3];
        String statisticsFile = args[4];


        PuzzleLoader loader = new PuzzleLoader();
        State initState = loader.load("src/main/resources/inputs/" + inputFile);
        Params params = new Params();
        ResultParams searchParams;
        switch (strategy) {
            case "bfs":
                params.setSearchOrder(new String[]{String.valueOf(strategyParam.charAt(0)), String.valueOf(strategyParam.charAt(1)), String.valueOf(strategyParam.charAt(2)), String.valueOf(strategyParam.charAt(3))});
                BreadthFirstSearch bfs = new BreadthFirstSearch();
                searchParams = bfs.execute(initState);
                break;
            case "dfs":
                params.setSearchOrder(new String[]{String.valueOf(strategyParam.charAt(0)), String.valueOf(strategyParam.charAt(1)), String.valueOf(strategyParam.charAt(2)), String.valueOf(strategyParam.charAt(3))});
                DepthFirstSearch dfs = new DepthFirstSearch();
                searchParams = dfs.execute(initState);
                break;
            case "astr":
                AStar aStar = new AStar();
                params.setSearchOrder(new String[]{"u", "d", "l", "r"});
                searchParams = aStar.execute(initState, strategyParam);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strategy);
        }

        try {
            FileWriter solutionWritter = new FileWriter("src/main/resources/outputs/"+solutionFile);
            solutionWritter.write(searchParams.getResultLength() + "\n");
            if(searchParams.getResultLength()>-1) {
                solutionWritter.write(searchParams.getResultPath() + "\n");
            }
            solutionWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter StatisticsWritter = new FileWriter("src/main/resources/outputs/"+statisticsFile);
            StatisticsWritter.write(searchParams.getResultLength() + "\n");
            StatisticsWritter.write(searchParams.getExplored() + "\n");
            StatisticsWritter.write(searchParams.getProcessed() + "\n");
            StatisticsWritter.write(searchParams.getMaxDepthRecursion() + "\n");
            StatisticsWritter.write(searchParams.getTime() + "\n");
            StatisticsWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
