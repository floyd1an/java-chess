package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{  //subclasse de Piece, as operações aqui podem ser aproveitadas em todas as outras peças
	
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
	
	protected boolean isThereOpponentPiece(Position position) { // verificar se existe uma peça adversária na posição de destino
		
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color; //se null ou cor diferente, então é uma peça adversária
	}

}
