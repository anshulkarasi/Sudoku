package flip.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
	private int size;
	private int blockSize;
	private Cell[][] matrix;
	private List<Constraint> rowsConstraints;
	private List<Constraint> columnsConstraints;
	private List<Constraint> blocksConstraints;
	
	/*//Size is a perfect sqaure
	public Board(int size){
		this.size = size;
		this.matrix = new Cell[size][size];
		
	}*/
	
	/* How to calculate blocksize?
	 * First, think about the relation between the index i in your array and the row and column numbers,
	 *  r and c. It is r=i/9 ,c=i(mod9). Then the row block, rb,(which horizontal block the index is in) 
	 *  and column block, cb (which vertical block the index is in) are
	 *   rb=i/27,cb=c/3
	 *   Finally the square s=3 *rb+cb
	 */
	
	public Board(int size,int[][] input){
		this.size = size;
		this.blockSize = (int) Math.sqrt(size);
		
		for(int i=0;i<size;i++)
			for(int j=0; j< size;j++)
				matrix[i][j].setValue(input[i][j]);
		
		rowsConstraints = new ArrayList<>(size);
		columnsConstraints = new ArrayList<>(size);
		blocksConstraints = new ArrayList<>(size);
		
		for(int i = 0; i < size; i++){
            rowsConstraints.add(new Constraint());
            columnsConstraints.add(new Constraint());
            blocksConstraints.add(new Constraint());
        }

	
		for(int i=0; i<size; i++){
			for(int j=0;j<size; j++){
				updateConstraints(i,j);
			}
		}	
	}
	
	private void updateConstraints(int i, int j){
		rowsConstraints.get(i).add(matrix[i][j]);
		columnsConstraints.get(j).add(matrix[i][j]);
		blocksConstraints.get(i  + j/blockSize).add(matrix[i][j]);
	}
	
	private void removeConstraints(int i, int j){
		rowsConstraints.get(i).remove(matrix[i][j]);
		columnsConstraints.get(j).remove(matrix[i][j]);
		blocksConstraints.get(i  + j/blockSize).remove(matrix[i][j]);
	}
	
	public void printBoard(){
		for(int i=0;i<size;i++){
			for(int j=0; j< size;j++)
				System.out.print(matrix[i][j].getValue() + "\t");
			System.out.println();
		}
			
	}
	
	/** Recursive Backtracking */
	public boolean solve(){
		if(solveRecursive(0,0)) return true;
		return false;
	}
	
	private boolean solveRecursive(int i, int j){
		if(i== size) return true;
		int nextj = (j < size-1)? j+1 : 0;
		int nexti = (j==0)? i+1: i;
		
		if(matrix[i][j].getValue()!=0){
			return solveRecursive(nexti,nextj);
		}
		else{
			for(int number = 1; number <= size;number++){
				if(isValid(number,i,j)){
					matrix[i][j].setValue(number);
					updateConstraints(i,j);
					
					if(solveRecursive(nexti,nextj)) return true;
					
					removeConstraints(i,j);
					matrix[i][j].setValue(0);
				}
			}		
		}		
		return false;
	}
	
	
	private boolean isValid(int number,int i, int j){
		return (rowsConstraints.get(i).isViolated(number) ||
				columnsConstraints.get(j).isViolated(number) ||
				blocksConstraints.get(i  + j/blockSize).isViolated(number));
	}
	
	public void print(){
		for(int i=0; i< size;i++){
			for(int j=0;j < size;j++){
				System.out.print(matrix[i][j].getValue() + "\t");
			}
			System.out.println();
		}
	}
}
