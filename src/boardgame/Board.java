package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces; // matriz peças

	// CONSTRUTORES

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // matriz de peças será instanciada na quantidade de rows e columns informadas
											

	}

	// GETTERS AND SETTERS

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//METODOS
	
	public Piece piece(int row, int column) {
		
		return pieces[row][column];
	}

	//SOBRECARGA
	
	public Piece piece(Position position) {   //retornará a peça por posição
		return pieces[position.getRow()][position.getColumn()];
	}
}
