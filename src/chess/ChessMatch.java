package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {  //classe dedicada às regras do sistema de xadrez
	
	private Board board;
	
	
	//CONSTRUTORES

	public ChessMatch() {
		
		board = new Board(8,8); //definindo o tamanho do tabuleiro
		initialSetup();  //inicia a partida colocando as peças no tabuleiro

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
	
	private void initialSetup() {  //inicia a partida colocando as peças no tabuleiro
		
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));

	}
	
}
