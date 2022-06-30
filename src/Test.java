import knox.sudoku.Sudoku;

public class Test {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.load("/Users/ujjwalsingh/Downloads/cs220-sudoku-main/easy1.txt");
        System.out.println(sudoku.progress());



    }
}
