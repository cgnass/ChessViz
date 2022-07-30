package game;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Rook extends Pieces{
	boolean hasMoved;
	public Rook(int x, int y, ID id) {
		super(x, y, id);
		hasMoved = false;
		try {
			if (id == ID.Player1) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_rlt60.png"));
			} else if (id == ID.Player2) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_rdt60.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void move(int x, int y) {
		this.setX(x);
		this.setY(y);
		hasMoved = true;
	}
	
	@Override
	public ArrayList<String> possibleMoves() {
		ArrayList<String> moves = new ArrayList<>();
		boolean isBlocked = false;
		int i = 1;
		while(!isBlocked) {
			String square = Board.hash(this.getX(), this.getY() - i);
			if(Board.boardState.containsKey(square)==true) {
				if(Board.boardState.get(square)==null) {
				moves.add(square);
				} else if(Board.boardState.get(square).id!=this.id){
				moves.add(square);
				isBlocked = true;
				} else {
					isBlocked = true;
				}
			} else {
				isBlocked = true;
			}
			i++;
		}
		i = 1;
		isBlocked = false;
		while(!isBlocked) {
			String square = Board.hash(this.getX(), this.getY() + i);
			if(Board.boardState.containsKey(square)==true) {
				if(Board.boardState.get(square)==null) {
				moves.add(square);
				} else if(Board.boardState.get(square).id!=this.id){
				moves.add(square);
				isBlocked = true;
				} else {
					isBlocked = true;
				}
			} else {
				isBlocked = true;
			}
			i++;
		}
		i = 1;
		isBlocked = false;
		while(!isBlocked) {
			String square = Board.hash(this.getX() - i, this.getY());
			if(Board.boardState.containsKey(square)==true) {
				if(Board.boardState.get(square)==null) {
				moves.add(square);
				} else if(Board.boardState.get(square).id!=this.id){
				moves.add(square);
				isBlocked = true;
				} else {
					isBlocked = true;
				}
			} else {
				isBlocked = true;
			}
			i++;
		}
		i = 1;
		isBlocked = false;
		while(!isBlocked) {
			String square = Board.hash(this.getX() + i, this.getY());
			if(Board.boardState.containsKey(square)==true) {
				if(Board.boardState.get(square)==null) {
				moves.add(square);
				} else if(Board.boardState.get(square).id!=this.id){
				moves.add(square);
				isBlocked = true;
				} else {
					isBlocked = true;
				}
			} else {
				isBlocked = true;
			}
			i++;
		}
		
		
		return moves;
	}
}
