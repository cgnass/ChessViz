package game;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class King extends Pieces {
	boolean hasMoved;
	Rook castling;
	boolean doCastle;
	public King(int x, int y, ID id) {
		super(x, y, id);
		hasMoved = false;
		doCastle = false;
		try {
			if (id == ID.Player1) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_klt60.png"));
			} else if (id == ID.Player2) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_kdt60.png"));
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
		ArrayList<String> remove = new ArrayList<>();
		for (int i = -1; i < 2; i++) {
			moves.add(Board.hash(this.getX() + i, this.getY() - 1));
			moves.add(Board.hash(this.getX() + i, this.getY() + 1));
			if (i != 0) {
				moves.add(Board.hash(this.getX() + i, this.getY()));
			}
		}
		for (String i : moves) {
			if (Board.boardState.containsKey(i) == false) {
				remove.add(i);
			}
		}
		for (String i : remove) {
			moves.remove(i);
		}
		
		if (hasMoved == false) {
			
			boolean isBlocked = false;
			int i = 1;
			while (isBlocked== false) {
				
				String square = Board.hash(this.getX() + i, this.getY());
				if (Board.boardState.get(square) instanceof Rook) {
					castling = (Rook) Board.boardState.get(square);
					if (castling.hasMoved == false) {
						moves.add(Board.hash(this.getX() + i - 1, this.getY()));
						isBlocked = true;
					} else {
						isBlocked = true;
					}
				} else if (Board.boardState.get(square) != null) {
					isBlocked = true;
				}
				i++;
			}
			isBlocked = false;
			i = 1;
			while (!isBlocked) {
				String square = Board.hash(this.getX() - i, this.getY());
				if (Board.boardState.get(square) instanceof Rook) {
					castling = (Rook) Board.boardState.get(square);
					if (castling.hasMoved == false) {
						moves.add(Board.hash(this.getX() - i + 2, this.getY()));
						isBlocked = true;
					} else {
						isBlocked = true;
					}
				} else if (Board.boardState.get(square) != null) {
					isBlocked = true;
				}
				i++;
			}

		}
		return moves;
	}
}
