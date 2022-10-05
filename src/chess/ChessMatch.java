package chess;

import boardgame.Position;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {  //classe dedicada às regras do sistema de xadrez
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	
	
	//CONSTRUTORES

	public ChessMatch() {
		
		board = new Board(8,8); //definindo o tamanho do tabuleiro
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();  //inicia a partida colocando as peças no tabuleiro

	}

	//GETS
	
	public int getTurn() {
		
		return turn;
	}
	
	public Color getCurrentPlayer() {
		
		return currentPlayer;
	}
	
	//METODOS
	
	public ChessPiece [][] getPieces(){  //retorna uma matriz de peças de xadrez correspondentes a partida
		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; /*percorre a matriz de peças do board e para cada
		 																		peça do tabuleiro sera feito um downcasting para ChessPiece */
		
		for(int i=0; i<board.getRows(); i++) {
			
			for(int j=0; j<board.getColumns(); j++) {
				
				mat[i][j] = (ChessPiece) board.piece(i, j); // downcasting
			}
		}
		return mat;	
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){  //imprime as posições possiveis a partir da posição de origem
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
		
		
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {  //move a peça de um lugar para outro
		
		Position source =  sourcePosition.toPosition();
		Position target =  targetPosition.toPosition();
		validateSourcePosition(source);  		//valida essa posição de origem, se não existir, passa uma exceção
		validateTargetPosition(source, target);  //validar posição de destino
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
		
	} 
	
	private Piece makeMove(Position source, Position target) {  //lógica de realizar um movimento
		
		Piece p = board.removePiece(source);  //remove a peça do local de origem
		Piece capturedPiece = board.removePiece(target);  //remove uma possivel peça no local de destino
		board.placePiece(p, target);  //coloca essa posição que estava na posição de origem na posição de destino
		
		if(capturedPiece != null) {   
			
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	 
	private void validateSourcePosition(Position position) {
		
		if(!board.thereIsAPiece(position)) {
			
			throw new ChessExceptions("There is no piece on source position");
		}
		
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			
			throw new ChessExceptions("The chosen piece is not yours");
		}
		
		if(!board.piece(position).isThereAnyPossibleMove()) { //testando se não tem  movimento possivel
			
			throw new ChessExceptions("There is not a possible moves for the chose piece ");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		
		if(!board.piece(source).possibleMove(target)){   		//se a posição de destino não é um movimento possivel, significa que não posso mover para lá 

		throw new ChessExceptions("There chosen piece can´t move to target position");
	}
	
	}
	
	private void nextTurn() {
		turn ++;
		
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;  // se o jogador atual for Color.White então agora ele vai ser Color.Black
		
	}
	
	
	private void placeNewPiece (char column, int row, ChessPiece piece) {
		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);   //coloca na lista de peças no tabuleiro
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
