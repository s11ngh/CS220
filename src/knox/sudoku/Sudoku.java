package knox.sudoku;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * 
 * This is the MODEL class. This class knows all about the
 * underlying state of the Sudoku game. We can VIEW the data
 * stored in this class in a variety of ways, for example,
 * using a simple toString() method, or using a more complex
 * GUI (Graphical User Interface) such as the SudokuGUI 
 * class that is included.
 * 
 * @author jaimespacco
 *
 */
public class Sudoku {
	int[][] board = new int[9][9];

	public String toFileString2(){ // I wrote this function as I noticed that the toFileString in SudokuGUI's Save wasn't working for some reason which caused the saved files to be empty
		String text = "";
		for(int r=0; r<9; r++){
			for(int c=0;c<9;c++){
				if (c==8){
					text=text+" "+ String.valueOf(board[r][c]);
					text=text+"\n";

				}

				else{text=text +" "+ String.valueOf(board[r][c]);}
			}
			}
		return text;
	}
	public ArrayList<Integer> toFileArray(){ // I wrote this function as I noticed that the toFileString in SudokuGUI's Save wasn't working for some reason which caused the saved files to be empty
		ArrayList<Integer> nums = new ArrayList<>();

		for(int r=0; r<9; r++){
			for(int c=0;c<9;c++){
				nums.add(board[r][c]);


			}
		}
		return nums;
	}
	
	public int get(int row, int col) {
		// TODO: check for out of bounds
		if(row<9 && row>=0 && col<9 && col>=0){
			return board[row][col];
		}
		else{throw new IndexOutOfBoundsException();}
	}
	
	public void set(int row, int col, int val) {
		// TODO: make sure val is legal
		if(val>-1 && val<10 && isLegal(row,col,val)){
			board[row][col] = val;


		}

	}

	public int progress(){
		int count = 0;
		for(int i=0; i<81; i++){
			if(toFileArray().get(i) ==0){
				count++;
			}
		}


		return count;
	}
	
	public boolean isLegal(int row, int col, int val) {
		// TODO: check if it's legal to put val at row, col

		for(int i: getLegalValues(row,col)){
			if(i==val){
				return true;
			}
		}
		return false;
	}
	
	public Collection<Integer> getLegalValues(int row, int col) {
		// TODO: return only the legal values that can be stored at the given row, col
		Set<Integer> result = new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));

		for(int i=0; i<9;i++){
			result.remove(board[row][i]);
			result.remove(board[i][col]);
		}
		int rstart = row/3*3;
		int cstart = col/3*3;
		for(int r=rstart; r<rstart+3;r++){
			for(int c=cstart; c<cstart+3;c++){
				result.remove(board[r][c]);
			}
		}
		return  result;
	}
	
/**

_ _ _ 3 _ 4 _ 8 9
1 _ 3 2 _ _ _ _ _
etc


0 0 0 3 0 4 0 8 9

 */
	public void load(File file) {
		try {
			Scanner scan = new Scanner(new FileInputStream(file));
			// read the file˳
			for (int r=0; r<9; r++) {
				for (int c=0; c<9; c++) {
					int val = scan.nextInt();
					board[r][c] = val;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void load(String filename) {
		load(new File(filename));
	}
	
	/**
	 * Return which 3x3 grid this row is contained in.
	 * 
	 * @param row
	 * @return
	 */
	public int get3x3row(int row) {
		return row / 3;
	}
	
	/**
	 * Convert this Sudoku board into a String
	 */
	public String toFileString() {
		String result = "";
		for (int r=0; r<9; r++) {
			for (int c=0; c<9; c++) {
				int val = get(r, c);
				result += "";

			}
			result += "\n";
		}
		return result;
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		sudoku.load("easy1.txt");
		System.out.println(sudoku);
		
		Scanner scan = new Scanner(System.in);
		while (!sudoku.gameOver()) {
			System.out.println("enter value r, c, v :");
			int r = scan.nextInt();
			int c = scan.nextInt();
			int v = scan.nextInt();
			sudoku.set(r, c, v);

			System.out.println(sudoku);
		}
	}

	public boolean gameOver() {
		// TODO check that there are still open spots
		for(int[] row: board){
			for(int val: row){
				if(val==0) return false;
			}
		}
		return false;
	}

	public boolean didIWin(){
		if(!gameOver()) return false;
		for(int r=0; r<9; r++){
			for(int c=0;c<9;c++){
				if (!isLegal(r, c, board[r][c])) return false;
			}
		}
		return true;
	}

	public boolean isBlank(int row, int col) {
		return board[row][col] == 0;
	}

}
