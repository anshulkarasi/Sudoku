package flip.game;

public class Cell {
	private int value;
	private boolean editable = false;
	
	public Cell(int value){
		this.value = value;
		if(value == 0) editable = true;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public boolean isEditable(){
		return editable;
	}
}
