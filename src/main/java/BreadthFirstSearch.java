import java.util.*;
import org.apache.commons.math3.util.Precision;
public class BreadthFirstSearch {
	Set<State> explored = new HashSet<>();
	ArrayDeque<State> openStates = new ArrayDeque<>();
	StringBuilder movesBuilder;

	public BreadthFirstSearch() {
		movesBuilder = new StringBuilder();
	}

	public ResultParams execute(State initState) {
		ResultParams resultParams = new ResultParams();
		long startTime = System.currentTimeMillis();

		if (StateUtils.isFinalState(initState)) {
			movesBuilder.append(initState.getPath()).reverse();
			printReport(startTime);
			resultParams.setResultLength(movesBuilder.toString().length());
			resultParams.setResultPath(movesBuilder.toString());
			resultParams.setMaxDepthRecursion(initState.getDepth());
			resultParams.setExplored(explored.size() + openStates.size());
			resultParams.setProcessed(explored.size());
			resultParams.setTime((long) Precision.round((System.currentTimeMillis() - startTime),3));
			System.out.println(openStates.size());
			System.out.println(explored.size());
			return resultParams;
		}

		openStates.add(initState);

		while (!openStates.isEmpty()) {
			State currentState = openStates.pollFirst();
			explored.add(currentState);

			if (StateUtils.isFinalState(currentState)) {
				movesBuilder.append(currentState.getPath()).reverse();
				printReport(startTime);
				resultParams.setResultLength(movesBuilder.toString().length());
				resultParams.setResultPath(movesBuilder.toString());
				resultParams.setMaxDepthRecursion(currentState.getDepth());
				resultParams.setExplored(explored.size() + openStates.size());
				resultParams.setProcessed(explored.size());
				resultParams.setTime((long) Precision.round((System.currentTimeMillis() - startTime),3));
				System.out.println(openStates.size());
				System.out.println(explored.size());
				return resultParams;
			}

			List<State> neighbours = StateUtils.getNeighbours(currentState);

			for (State neighbour : neighbours) {

				neighbour.addPath(currentState.getPath());

				if (!explored.contains(neighbour)) {

					if (StateUtils.isFinalState(neighbour)) {
						movesBuilder.append(neighbour.getPath()).reverse();
						printReport(startTime);
						resultParams.setResultLength(movesBuilder.toString().length());
						resultParams.setResultPath(movesBuilder.toString());
						resultParams.setMaxDepthRecursion(neighbour.getDepth());
						resultParams.setExplored(explored.size() + openStates.size());
						resultParams.setProcessed(explored.size());
						resultParams.setTime((long) Precision.round((System.currentTimeMillis() - startTime),3));
						System.out.println(openStates.size());
						System.out.println(explored.size());
						return resultParams;
					}
					openStates.add(neighbour);

				} else {
					System.out.println("already been there");
				}
			}


		}
		resultParams.setResultLength(-1);
		return resultParams;
	}
	private void printReport(long startTime) {
		System.out.println("d??ugo???? znalezionego rozwi??zania: " + movesBuilder.toString().length() + "\n" +
				"liczb?? stan??w odwiedzonych: " + (explored.size() + openStates.size()) + "\n" +
				"liczb?? stan??w przetworzonych: " + explored.size() + "\n" +
				"maksymaln?? osi??gni??t?? g????boko???? rekursji:\n" +  //NIE DOTYCZY BFS
				"czas trwania procesu obliczeniowego:" + (System.currentTimeMillis() - startTime) + " [ms]");
	}
}