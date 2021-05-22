import java.util.*;

public class AStar {
	Set<State> explored = new HashSet<>(); //może tutaj dodawać jedynie hashcode układu a nie cały obiekt i liczyć że GC zadziała
	ArrayList<State> openStates = new ArrayList<>();
	StringBuilder movesBuilder;
	AbstractHeuristic heuristic;

	public AStar() {
		movesBuilder = new StringBuilder();
	}

	public ResultParams execute(State initState) {
		String method = "manh";
		switch (method) {
			case "manh":
				heuristic = new ManhattanHeuristic(initState.getWidth(), initState.getHeight());
				break;
			case "hamm":
				heuristic = new HammingHeuristic(initState.getWidth(), initState.getHeight());
				break;
			default:
				throw new RuntimeException("brak impementacji heurystyki: " + method);
		}


		ResultParams resultParams = new ResultParams();
		long startTime = System.currentTimeMillis();


		if (StateUtils.isFinalState(initState)) {
			printReport(startTime);
			resultParams.setResultLength(movesBuilder.toString().length());
			resultParams.setResultPath(movesBuilder.toString());
			return resultParams;
		}

		openStates.add(initState);

		while (!openStates.isEmpty()) {
			State currentState = openStates.get(0);
			openStates.remove(0);
			explored.add(currentState);

			/***************
			fx() = g(x) + h(x)
			Funkcja g(x) określa rzeczywisty koszt dojścia do punktu x (aktualny koszt dojścia do węzła)
			Funkcja h(x) to funkcja heurystyczna. Oszacowuje zawsze optymistycznie koszt dotarcia  od punktu x do wierzchołka docelowego
			chcemy zeby heurystyka była jak najmniejsza

			Warunek dopuszczalności g(x) + h(x) <= g(xt) gdzie xt to punkt koncowy
			Warunek monotoniczności g(xj) + h(xj) >- g(xi) + h(xi) gdzie j>i (warunek spójności)

			Warunek dopuszczalności to nadmierny optymizm
			warunek monotonicznosci mówi o tym że im bardziej przyblizamy się do rozwiązania tym oszacowanie musi być coraz mniej optymistyczne
			******************/

			List<State> neighbours = StateUtils.getNeighbours(currentState);
			for (State neighbour : neighbours) {
				//jeżeli neighbour jest w explored lub open states to pomijamy go
				if (explored.contains(neighbour) || openStates.contains(neighbour)) {
					continue;
				}

				if (StateUtils.isFinalState(neighbour)) {
					printReport(startTime);
					resultParams.setResultLength(movesBuilder.toString().length());
					resultParams.setResultPath(movesBuilder.toString());
					return resultParams;
				} else {
					neighbour.setFScore(heuristic.distance(neighbour) + neighbour.getDepth());
					addToOpenStateWithFScoreOrder(neighbour);
				}
			}
		}
		//nie udało się
		return resultParams;
	}

	private void addToOpenStateWithFScoreOrder(State stateToAdd) {
		int newStateFScore = stateToAdd.getFScore();
		for (int i = 0; i < openStates.size(); i++) {
			if (newStateFScore <= openStates.get(i).getFScore()) {
				openStates.add(i, stateToAdd);
				return;
			}
		}
		openStates.add(stateToAdd);
	}


	private void printReport(long startTime) {
		System.out.println("długość znalezionego rozwiązania: " + movesBuilder.toString().length() + "\n" +
				"liczbę stanów odwiedzonych: " + explored.size() + "\n" +
				"liczbę stanów przetworzonych: " + (explored.size() + openStates.size()) + "\n" +  //NIE JESTEM PEWIEN
				"maksymalną osiągniętą głębokość rekursji:\n" +  //NIE DOTYCZY BFS
				"czas trwania procesu obliczeniowego:" + (System.currentTimeMillis() - startTime) + " [ms]");
	}

	class StateCostComparator implements Comparator<State> {
		@Override
		public int compare(State a, State b) {
			return -1 * a.getTotalCost().compareTo(b.getTotalCost());
		}
	}
}
