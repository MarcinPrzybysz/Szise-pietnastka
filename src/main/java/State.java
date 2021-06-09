import lombok.Data;

import java.util.Arrays;

@Data
public class State {
	private int height;
	private int width;
	private int[][] arrangement;
	private Position gapPosition;
	private String moveDirection;
	private int depth = 0;
	private int pathCost;
	private int fScore = 0;
	private String path = "";

	public Integer getTotalCost(){
		return pathCost + depth;
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
			newState.setDepth(depth + 1);

			return newState;
		}
		return null;
	}

	private boolean isMovePossible(int moveX, int moveY) {
		if (gapPosition.getX() + moveX < 0 || gapPosition.getX() + moveX > height - 1) {
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

	public int getPositionValue(int x, int y) {
		return arrangement[x][y];
	}

	public void calculatePathCost() {
		pathCost = StateUtils.hamming(this);
//		cost = StateUtils.manhattan(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		State state = (State) o;
		return Arrays.equals(arrangement, state.arrangement);
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this.hashCode() == o.hashCode()) return true;
//		if (o == null || getClass().hashCode() != o.getClass().hashCode()) return false;
//		State state = (State) o;
//		return Arrays.equals(arrangement, state.arrangement);
//	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(arrangement);
	}

	public void setFScore(int fScore) {
		this.fScore = fScore;
	}

	public int getFScore() {
		return fScore;
	}

	public void addPath(String s){
		path+=s;
	}

	public String getPath() {
		return path;
	}
}
