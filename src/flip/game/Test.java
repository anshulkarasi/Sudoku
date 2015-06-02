package flip.game;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		
		int[][] matrix = new int[size][size];
		
		for(int i=0;i < size;i++)
			for(int j=0; j<size;j++)
				matrix[i][j] = in.nextInt();
		
		Board sol = new Board(size,matrix);
		
		if(sol.solve()) sol.print();
		else System.out.println("No solution");
	}

}
