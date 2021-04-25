import java.util.*;

public class BreadthFirstSearch {
	Set<State> visited = new HashSet<>();
	Queue<State> openStates = new ArrayDeque<>();


	//todo:
//  długość znalezionego rozwiązania;
//  liczbę stanów odwiedzonych;
//  liczbę stanów przetworzonych;
//  maksymalną osiągniętą głębokość rekursji;
//  czas trwania procesu obliczeniowego.

	public State execute(State initState) {
		openStates.add(initState);

		while (!openStates.isEmpty()) {
			State currentState = openStates.poll();
			visited.add(currentState);

			if (StateUtils.isFinalState(currentState)) {
				return currentState;
			}

			List<State> neighbours = StateUtils.getNeighbours(currentState);
			for (State neighbour: neighbours) {
				if (!visited.contains(neighbour)) {
					openStates.add(neighbour);
				}else {
					System.out.println("already been there");
				}
			}

		}
		return null;
	}

}
