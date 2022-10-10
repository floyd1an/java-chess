package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	private ChessMatch chessMatch;
	
	
	//METODOS
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	
	public String toString() {
		
		return "K";
	}
	
	
	private boolean canMove(Position position) {
		
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor(); 
		
	}
	
	private boolean testRookCastling(Position position) {   //testar se na posição informada existe uma torre e verificar se ela está apta para o roque
		
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0; //se tudo estiver de acordo a torre está apta para o roque
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];  //matriz da mesma dimensão do tabuleiro
		
		
		Position p = new Position(0, 0);
		
		//acima
		
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//abaixo
		
		p.setValues(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//esquerda
		
		p.setValues(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//direita
		
		p.setValues(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//nw
		
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//ne
		
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
					
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sw
		
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
					
		mat[p.getRow()][p.getColumn()] = true;
		}		
		
		//se
		
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
					
		mat[p.getRow()][p.getColumn()] = true;
		
		}
		
		//Movimento especial Roque
		
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			
			//roque pequeno
			
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastling(posT1)) {
				
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}

			}
			//roque grande
			
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastling(posT2)) {
				
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);

				
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}

			}
		}
		
		return mat;
	}

}
