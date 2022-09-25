package chess;

import boardgame.Position;

import boardgame.Board;
import boardgame.Piece;
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
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {  //move a peça de um lugar para outro
		
		Position source =  sourcePosition.toPosition();
		Position target =  targetPosition.toPosition();
		ValidateSourcePosition(source);  //valida essa posição de origem, se não existir, passa uma exceção
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
		
	} 
	
	private Piece makeMove(Position source, Position target) {  //lógica de realizar um movimento
		
		Piece p = board.removePiece(source);  //remove a peça do local de origem
		Piece capturedPiece = board.removePiece(target);  //remove uma possivel peça no local de destino
		board.placePiece(p, target);  //coloca essa posição que estava na posição de origem na posição de destino
		return capturedPiece;
	}
	
	private void ValidateSourcePosition(Position position) {
		
		if(!board.thereIsAPiece(position)) {
			
			throw new ChessExceptions("There is no piece on source position");
		}
	}
	
	private void placeNewPiece (char column, int row, ChessPiece piece) {
		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	
	private void initialSetup() {  //inicia a partida colocando as peças no tabuleiro
		
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	
	}
	
}
