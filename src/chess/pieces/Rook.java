package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	
	
	//METODOS
	
	public Rook(Board board, Color color) {
		super(board, color);
	}

	
	@Override
	
	public String toString() {
		
		return "R";
		
	}
	
	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];  //matriz da mesma dimensão do tabuleiro
		
		return mat;
	}
	
}
