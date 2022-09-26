package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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
		
		Position p = new Position(0, 0);
		
		
		//acima -- linha vertical  
				p.setValues(position.getRow() - 1, position.getColumn());
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {       //enquanto a posição estiver vaga ela será verdadeira 
					
					mat[p.getRow()][p.getColumn()] = true;
					p.setRow(p.getRow() - 1 );
				} 
				
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					
					mat[p.getRow()][p.getColumn()] = true;
		
				}
		
		//esquerda -- linha horizontal  
				p.setValues(position.getRow(), position.getColumn() - 1);
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {       //enquanto a posição estiver vaga ela será verdadeira 
					
					mat[p.getRow()][p.getColumn()] = true;
					p.setColumn(p.getColumn() - 1 );
				} 
				
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					
					mat[p.getRow()][p.getColumn()] = true;

				}
				
		//direita -- linha horizontal  
				p.setValues(position.getRow(), position.getColumn() + 1);
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {       //enquanto a posição estiver vaga ela será verdadeira 
					
					mat[p.getRow()][p.getColumn()] = true;
					p.setColumn(p.getColumn() + 1 );
				} 
				
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					
					mat[p.getRow()][p.getColumn()] = true;

				}
				
		//abaixo -- linha vertical  
				p.setValues(position.getRow() + 1, position.getColumn());
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {       //enquanto a posição estiver vaga ela será verdadeira 
					
					mat[p.getRow()][p.getColumn()] = true;
					p.setRow(p.getRow() + 1 );
				} 
				
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					
					mat[p.getRow()][p.getColumn()] = true;

				}	
		
		return mat;
	}
	
}
