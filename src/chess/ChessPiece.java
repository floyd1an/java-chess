package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece{  //subclasse de Piece
	
	private Color color;


	//CONSTRUTORES
	
	public ChessPiece(Board board, Color color) {
		super(board);  //herança da classe piece
		this.color = color;
	}


	
	
	// GETTERS AND SETTERS

	public Color getColor() {
		return color;
	}


	/*public void setColor(Color color) {  A cor da peça deve apenas ser acessada e não modificada
		this.color = color;
	}*/
	
	

}
