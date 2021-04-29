import lombok.Data;

@Data
public class State {
	private int height;
	private int width;
	private int[][] arrangement;
	private Position gapPosition;
	private String moveDirection;
	private int depth = 0;

	public State move(int moveX, int moveY) {
		if (isMovePossible(moveX, moveY)) {
			State newState = copyOf();
			int[][] arrangement = newState.getArrangement();
			Position gapPosition = newState.getGapPosition();

			int adjoiningValue = arrangement[gapPosition.getY() + moveY][gapPosition.getX() + moveX];
			arrangement[gapPosition.getY() + moveY][gapPosition.getX() + moveX] = 0;
			arrangement[gapPosition.getY()][gapPosition.getX()] = adjoiningValue;

			gapPosition.setX(gapPosition.getX() + moveX);
			gapPosition.setY(gapPosition.getY() + moveY);
			newState.setArrangement(arrangement);
			newState.setDepth(depth + 1);

			return newState;
		}
		return null;
	}

	private boolean isMovePossible(int moveX, int moveY) {
		if (gapPosition.getX() + moveX < 0 || gapPosition.getX() + moveX > width - 1) {
			return false;
		}
		if (gapPosition.getY() + moveY < 0 || gapPosition.getY() + moveY > width - 1) {
			return false;
		}
		return true;
	}

	private State copyOf() {
		State copyState = new State();
		copyState.setWidth(width);
		copyState.setHeight(height);
		copyState.setGapPosition(new Position(gapPosition.getX(), gapPosition.getY()));
		copyState.setArrangement(copyArray(arrangement));
		return copyState;
	}

	private int[][] copyArray(int[][] arr) {
		int[][] newArr = new int[arr.length][arr[0].length];
		for(int i = 0; i<arr.length;i++) {
			System.arraycopy(arr[i], 0, newArr[i], 0, arr[0].length);
		}
		return newArr;
	}
}
