package game;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Queen extends Pieces{

	public Queen(int x, int y, ID id) {
		super(x, y, id);
		try {
			if (id == ID.Player1) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_qlt60.png"));
			} else if (id == ID.Player2) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_qdt60.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
@Override
public ArrayList<String> possibleMoves() {
	ArrayList<String> moves = new ArrayList<>();
	boolean isBlocked = false;
	int i = 1;
	while(!isBlocked) {
		String square = Board.hash(this.getX() + i, this.getY() + i);
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
		String square = Board.hash(this.getX() - i, this.getY() - i);
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
		String square = Board.hash(this.getX() - i, this.getY() + i);
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
		String square = Board.hash(this.getX() + i, this.getY() - i);
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
