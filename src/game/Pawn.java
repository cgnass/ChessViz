package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pawn extends Pieces {
	boolean hasMoved;
	boolean canE;
	boolean canPromote;
	

	public Pawn(int x, int y, ID id) {
		super(x, y, id);
		hasMoved = false;
		canPromote = false;
		boolean canE;
		try {
			if (id == ID.Player1) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_plt60.png"));
			} else if (id == ID.Player2) {
				image = ImageIO.read(getClass().getResource("/game/images/Chess_pdt60.png"));
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
		if(y==0||y==7) {			
			canPromote = true;
		}
		
	}

	@Override
	public ArrayList<String> possibleMoves() {
		ArrayList<String> moves = new ArrayList<>();
		int pawnMove = (this.id == ID.Player1) ? -1 : 1;
		String move1 = Board.hash(this.getX(), pawnMove + this.getY());
		String move2 = Board.hash(this.getX(), pawnMove * 2 + this.getY());
		String eat1 = Board.hash(this.getX() + pawnMove, pawnMove + this.getY());
		String eat2 = Board.hash(this.getX() - pawnMove, pawnMove + this.getY());
		String enP1 = Board.hash(this.getX() - 1, this.getY());
		String enP2 = Board.hash(this.getX() + 1, this.getY());
		if (hasMoved == true) {
			if (Board.boardState.get(move1) == null) {
				moves.add(move1);
			}
			if (Board.boardState.get(eat1) != null && Board.boardState.get(eat1).id != this.id) {
				moves.add(eat1);
			}
			if (Board.boardState.get(eat2) != null && Board.boardState.get(eat2).id != this.id) {
				moves.add(eat2);
			}
		} else {
			moves.add(move2);
			moves.add(move1);
			if (Board.boardState.get(eat1) != null && Board.boardState.get(eat1).id != this.id) {
				moves.add(eat1);
			}
			if (Board.boardState.get(eat2) != null && Board.boardState.get(eat2).id != this.id) {
				moves.add(eat2);
			}
		}

		if (Board.boardState.get(enP1) != null && Board.boardState.get(enP1) instanceof Pawn) {
			Pawn pawn = (Pawn) Board.boardState.get(enP1);
			if (pawn.canE == true) {
				moves.add(Board.hash(this.getX() - 1, this.getY() + pawnMove));
			}
		}

		if (Board.boardState.get(enP2) != null && Board.boardState.get(enP2) instanceof Pawn) {
			Pawn pawn = (Pawn) Board.boardState.get(enP2);
			if (pawn.canE == true) {
				moves.add(Board.hash(this.getX() + 1, this.getY() + pawnMove));
			}
		}
		return moves;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(image, Board.squares[0][x], Board.squares[1][y], null);
		Image image1 = null, image2 = null, image3 = null, image4 = null, image5 = null, image6 = null, image7 = null, image8 = null;;
		
		if(Pieces.selected[0] == this) {
			g.setColor(Color.RED);
			g.drawRect(Board.squares[0][x], Board.squares[1][y], Board.getSize(), Board.getSize());
		}
		if(ID.Player1==this.id&&y==0) {
			
			try {
				image1 = ImageIO.read(getClass().getResource("/game/images/Chess_qlt60.png"));
				image2 = ImageIO.read(getClass().getResource("/game/images/Chess_rlt60.png"));
				image3 = ImageIO.read(getClass().getResource("/game/images/Chess_blt60.png"));
				image4 = ImageIO.read(getClass().getResource("/game/images/Chess_nlt60.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.setColor(Color.GRAY);
			g.fillRect(Board.squares[0][x], Board.squares[1][y]- Board.getSize(), 4*Board.getSize(), Board.getSize());
			g.drawImage(image1, Board.squares[0][x], Board.squares[1][y] - Board.getSize(), null);
			g.drawImage(image2, Board.squares[0][x]+Board.getSize(), Board.squares[1][y] - Board.getSize(), null);
			g.drawImage(image3, Board.squares[0][x]+2*Board.getSize(), Board.squares[1][y] - Board.getSize(), null);
			g.drawImage(image4, Board.squares[0][x]+3*Board.getSize(), Board.squares[1][y] - Board.getSize(), null);
		} else if(ID.Player2==this.id&&y==7) {
			try {
				image5 = ImageIO.read(getClass().getResource("/game/images/Chess_qdt60.png"));
				image6 = ImageIO.read(getClass().getResource("/game/images/Chess_rdt60.png"));
				image7 = ImageIO.read(getClass().getResource("/game/images/Chess_bdt60.png"));
				image8 = ImageIO.read(getClass().getResource("/game/images/Chess_ndt60.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.setColor(Color.GRAY);
			g.fillRect(Board.squares[0][x], Board.squares[1][y]+Board.getSize(), 4*Board.getSize(), Driver.WIDTH);
			g.drawImage(image5, Board.squares[0][x], Board.squares[1][y] + Board.getSize(), null);
			g.drawImage(image6, Board.squares[0][x+1], Board.squares[1][y] + Board.getSize(), null);
			g.drawImage(image7, Board.squares[0][x+2], Board.squares[1][y] + Board.getSize(), null);
			g.drawImage(image8, Board.squares[0][x+3], Board.squares[1][y] + Board.getSize(), null);
		}
	}

}
