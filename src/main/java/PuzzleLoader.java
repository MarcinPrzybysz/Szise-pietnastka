import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleLoader {

	public State load(String path) {
		BufferedReader reader;
		State state = new State();
		Position gapPosition = new Position();
		try {
			reader = new BufferedReader(new FileReader(
					path));
			String line = reader.readLine();
			String [] puzzleSize =  line.split("\\s+");
			state.setHeight(Integer.parseInt(puzzleSize[0]));
			state.setWidth(Integer.parseInt(puzzleSize[1]));

			int[][] puzzleState = new int[state.getHeight()][state.getWidth()];

			for (int i = 0; i < state.getHeight() ; i++) {
				line = reader.readLine();
				String [] rowValues = line.split("\\s+");
				for (int j = 0; j < state.getHeight() ; j++) {
					puzzleState[i][j] = Integer.parseInt(rowValues[j]);
					if (Integer.parseInt(rowValues[j]) == 0) {
						gapPosition.setX(j);
						gapPosition.setY(i);
					}
				}
			}
			reader.close();
			state.setGapPosition(gapPosition);
			state.setArrangement(puzzleState);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return state;
	}

}
