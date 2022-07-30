package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pieces extends GameObject {
	protected Image image;
	static protected Pieces[] selected = new Pieces[1];
	

	public Pieces(int x, int y, ID id) {
		super(x, y);
		this.id = id;
		Board.boardState.put(Board.hash(x, y), this);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, Board.squares[0][x], Board.squares[1][y], null);
		if(Pieces.selected[0] == this) {
			g.setColor(Color.RED);
			g.drawRect(Board.squares[0][x], Board.squares[1][y], Board.getSize(), Board.getSize());
		}
	}
	
	public void move(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public ArrayList<String> possibleMoves() {
		return null;
	}
	public void moveProccessing(int oldX, int oldY) {
		Board.boardState.replace(Board.hash(oldX, oldY), null);
		int x = Pieces.selected[0].getX();
		int y = Pieces.selected[0].getY();
		Board.boardState.replace(Board.hash(x, y), Pieces.selected[0]);
	
	}
	
	
}
