package boardgame;

public class Position {
	
	
	private int row;   // linha
	private int column;// coluna 
	
	//CONSTRUTORES
	
	public Position() {
		
	}
	
	public Position(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	//GETTERS AND SETTERS
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	//METODOS
	
	public void setValues(int row, int column) {
		
		
	}
	
	
	@Override
	
	public String toString() {
		
		return row + ", " + column;
	}
	
}