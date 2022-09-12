package chess;

import boardgame.Board;

public class ChessMatch {  //classe dedicada às regras do sistema de xadrez
	
	private Board board;
	
	
	//CONSTRUTORES

	public ChessMatch() {
		
		board = new Board(8,8);  //definindo o tamanho do tabuleiro
	}

	
	//METODOS
	
	public ChessPiece [][] GetPieces(){  //retorna uma matriz de peças de xadrez correspondentes a partida
		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; /*percorre a matriz de peças do board e para cada
		 																		peça do tabuleiro sera feito um downcasting para ChessPiece */
		
		for(int i=0; i<board.getRows(); i++) {
			
			for(int j=0; j<board.getColumns(); j++) {
				
				mat[i][j] = (ChessPiece) board.piece(i, j); // downcasting
			}
		}
		return mat;	
	}
	
	
}
