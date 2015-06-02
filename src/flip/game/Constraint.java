package flip.game;

import java.util.HashSet;
import java.util.Set;

public class Constraint {
	Set<Cell> cells = new HashSet<>();
	
	public Constraint(){}
	
	public void add(Cell cell){
		this.cells.add(cell);
	}
	
	public boolean isViolated(int number){
		for (Cell cell : cells){
			if(cell.getValue() == number) return true;
		}
		return false;
	}
	
	public void remove(Cell cell){
		cells.remove(cell);
	}
}
