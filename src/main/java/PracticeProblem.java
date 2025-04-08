public class PracticeProblem {

	public static void main(String args[]) {

	}

	public static int searchMazeMoves(String[][] arr) {
        int startRow = arr.length - 1; // gives you the amount of rows in index format
        int startCol = 0;
        int[] result = dfsHelper(arr, startRow, startCol, 0, 0);
        return result[0]; // return the minimum number of moves
    }

	public static int noOfPaths(String[][] arr) {
		int startRow = arr.length - 1;
		int startCol = 0;
        int[] result = dfsHelper(arr, startRow, startCol, 0, 0);
		return result[1];
	}

	public static int[] dfsHelper(String[][] arr, int row, int col, int moves, int paths) { //row < 0 or col < 0: you're above or to the left of the maze.
		if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length) { //row >= arr.length or col >= arr[0].length: you're below or to the right of the maze.
            return new int[]{-1, paths}; // returns invalid (-1) and paths is returned as is since it didn't reach f
        } else if (arr[row][col].equals("F")) { // checks if we've reached the finish point 
            return new int[]{moves, paths + 1}; //returns the amount of moves and iterates path by 1 showing that it's a valid path
        } if (arr[row][col].equals("*")) {
			return new int[]{-1, paths}; // returns invalid(-1) if it hits a block(*) and paths is returned as is since it didn't reach f
		}


        int[] moveUp = dfsHelper(arr, row - 1, col, moves + 1, paths);
        int[] moveRight = dfsHelper(arr, row, col + 1, moves + 1, paths);

        if (moveUp[0] == -1) return moveRight; // if you can't move up go right
        if (moveRight[0] == -1) return moveUp; // if you can't move right go up

        int minMoves = Math.min(moveUp[0], moveRight[0]); //takes the smaller of the 2 and returns the one w the least amount of moves 
        int totalPaths = moveUp[1] + moveRight[1];
        return new int[]{minMoves, totalPaths}; //(moveUp = 5, 1) (moveRight = 4, 1) returns {4, 2} - 4 is the least amount of moves 2 is the amount of successful paths
    }
}


