package boardgame;

public class Piece {

	protected Position position;  //posição inicial da peça antes de entrar no tabuleiro - será nula
	private Board board;
	
	
	
	//CONSTRUTORES
	
	public Piece(Board board) {
		
		this.board = board;
		position = null;
	}


	//GETTERS AND SETTERS
	
	protected Board getBoard() {
		return board;
	}
	
	
	
}
