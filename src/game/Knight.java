package game;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Knight extends Pieces {

	public Knight(int x, int y, ID id) {
		super(x, y, id);
		try {
			if (id == ID.Player1) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_nlt60.png"));
			} else if (id == ID.Player2) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_ndt60.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> possibleMoves() {
		ArrayList<String> moves = new ArrayList<>();
		ArrayList<String> remove = new ArrayList<>();
		moves.add(Board.hash(this.getX()+2, this.getY()-1));
		moves.add(Board.hash(this.getX()+2, this.getY()+1));
		moves.add(Board.hash(this.getX()-2, this.getY()+1));
		moves.add(Board.hash(this.getX()-2, this.getY()-1));
		moves.add(Board.hash(this.getX()-1, this.getY()+2));
		moves.add(Board.hash(this.getX()+1, this.getY()+2));
		moves.add(Board.hash(this.getX()-1, this.getY()-2));
		moves.add(Board.hash(this.getX()+1, this.getY()-2));
		for(String i : moves) {
			if(Board.boardState.containsKey(i) == false) {
				remove.add(i);
			}
		}
		for(String i : remove) {
			moves.remove(i);
		}
		return moves;
	}

}
