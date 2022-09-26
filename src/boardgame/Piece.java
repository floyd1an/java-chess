package boardgame;

public abstract class Piece {

	protected Position position;  //posição inicial da peça antes de entrar no tabuleiro - será nula
	private Board board;
	
	
	
	//CONSTRUTORES
	
	public Piece(Board board) {
		
		this.board = board;
		position = null;
	}


	//GETTERS AND SETTERS
	
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() { //percorre a matriz possibleMoves para achar se existe algum movimento possivel
		
		boolean[][] mat = possibleMoves();
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
				
				if(mat[i][j]) {  //percorre a matriz para verififcar se a condição é verdadeira
					
					return true;
				}
			}
		}
			return false; //se a condição for falsa, ou seja, não existe um movimento possivel para a peça
	}
	
	
}
