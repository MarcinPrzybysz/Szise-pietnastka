public class State {
	private int height;
	private int width;
	private int[][] arrangement;
	private Position gapPosition;


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int[][] getArrangement() {
		return arrangement;
	}

	public void setArrangement(int[][] arrangement) {
		this.arrangement = arrangement;
	}

	public Position getGapPosition() {
		return gapPosition;
	}

	public void setGapPosition(Position gapPosition) {
		this.gapPosition = gapPosition;
	}

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
		for(int i =0; i<arr.length;i++) {
			for(int j =0; j<arr[0].length;j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		return newArr;
	}
}
