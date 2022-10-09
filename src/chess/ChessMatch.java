package chess;

import boardgame.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {  //classe dedicada às regras do sistema de xadrez
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if(testCheck(currentPlayer)) {  //testa se o movimento feito colocou o próprio jogador em check, o que não é permitido
			undoMove(source, target, capturedPiece);
			throw new ChessExceptions("You can´t put yourself in check");
		} 
		
		check = (testCheck(opponent(currentPlayer))) ? true : false; 
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}else {
		
		nextTurn();
	}
		return (ChessPiece) capturedPiece;
		
	} 
	
	private Piece makeMove(Position source, Position target) {  //lógica de realizar um movimento
		
		ChessPiece p = (ChessPiece)board.removePiece(source);  //remove a peça do local de origem
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);  //remove uma possivel peça no local de destino
		board.placePiece(p, target);  //coloca essa posição que estava na posição de origem na posição de destino
		
		if(capturedPiece != null) {   
			
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
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
	
	
	private Color opponent(Color color) {
		
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king (Color color) {
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		for(Piece p : list) {
			
			if( p instanceof King) {
				return (ChessPiece)p;
			}
		}
		
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {    //verifica se o rei desta cor está em check
		Position kingPosition = king(color).getChessPosition().toPosition(); //pega a posição do rei
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		for(Piece p : opponentPieces) { //testa se existe algum movimento inimigo possivel que leva até a posição do rei
			boolean [][] mat = p.possibleMoves();  //se nessa matriz a posição correspondente a posição do rei for true significa que o rei está em check
			if( mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				
			return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		
		if(!testCheck(color)) {
			
			return false;
		}
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		for(Piece p : list) {  //se esgotar o FOR e não encontrar nenhum movimento possivel para tirar do check, então será checkMate
			
			boolean[][] mat = p.possibleMoves();
			for(int i=0; i<board.getRows(); i++) {
				for(int j=0; j<board.getColumns(); j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
						 
					}
				}
			}
		}
		return true;
	}
	
	
	private void initialSetup() {  //inicia a partida colocando as peças no tabuleiro
		
		  	placeNewPiece('a', 1, new Rook(board, Color.WHITE));
	        placeNewPiece('e', 1, new King(board, Color.WHITE));
	        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
	        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

	        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('e', 8, new King(board, Color.BLACK));
	        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	
	}
	
}
