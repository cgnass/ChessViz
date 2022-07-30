package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseInput extends MouseAdapter {
	private int size;
	private boolean isChecked;
	private Handler handler;
	static private ID turn;
	static public GameObject selectedPiece;
	Pawn ePawn;
	boolean isPromoting;

	public MouseInput(Handler handler) {
		size = Board.getSize();
		isChecked = false;
		this.handler = handler;
		turn = ID.Player1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isPromoting) {
			int x = mouseOnBoardX(e.getX());
			int y = mouseOnBoardY(e.getY());
			if (y == -1 || x == -1) {
				return;
			}
			if (x == 1) {
				Board.boardState.replace(Board.hash(Pieces.selected[0].getX(), Pieces.selected[0].getY()), null);
				handler.addObject(new Queen(Pieces.selected[0].getX(), Pieces.selected[0].getY(), turn));
				handler.removeObject(Pieces.selected[0]);
			} else if (x == 2) {
				Board.boardState.replace(Board.hash(Pieces.selected[0].getX(), Pieces.selected[0].getY()), null);
				handler.addObject(new Rook(Pieces.selected[0].getX(), Pieces.selected[0].getY(), turn));
				handler.removeObject(Pieces.selected[0]);
			} else if (x == 3) {
				Board.boardState.replace(Board.hash(Pieces.selected[0].getX(), Pieces.selected[0].getY()), null);
				handler.addObject(new Bishop(Pieces.selected[0].getX(), Pieces.selected[0].getY(), turn));
				handler.removeObject(Pieces.selected[0]);
			} else if (x == 4) {
				Board.boardState.replace(Board.hash(Pieces.selected[0].getX(), Pieces.selected[0].getY()), null);
				handler.addObject(new Knight(Pieces.selected[0].getX(), Pieces.selected[0].getY(), turn));
				handler.removeObject(Pieces.selected[0]);
			}
			Pieces.selected[0].getX();
			Pieces.selected[0] = null;
			this.switchTurns();
			isPromoting = false;
			return;
		}
		int x = mouseOnBoardX(e.getX());
		int y = mouseOnBoardY(e.getY());

		ArrayList<String> possibleMoves = null;
		Pieces piece = Board.boardState.get(Board.hash(x, y));
		if (x == -1 || y == -1) {
			return;
		}
		if (Pieces.selected[0] != null) {
			possibleMoves = Pieces.selected[0].possibleMoves();
		}
		if (piece != null && piece.id == turn) {
			Pieces.selected[0] = Board.boardState.get(Board.hash(x, y));
		} else if (Pieces.selected[0] != null) {
			this.selectMove(x, y, possibleMoves);
		}
	}

	private int mouseOnBoardX(int mouseX) {
		if (isPromoting) {
			if (mouseX < Board.squares[0][Pieces.selected[0].getX()]
					|| mouseX > Board.squares[0][Pieces.selected[0].getX()] + size * 4) {
				return -1;
			}
			for (int i = 1; i <= 4; i++) {
				if (mouseX < Board.squares[0][Pieces.selected[0].getX()] + size * i) {
					return i;
				}
			}
		}
		if (mouseX < Board.squares[0][0] || mouseX > Board.squares[0][7] + size) {
			return -1;
		}
		for (int i = 0; i < Board.squares[0].length; i++) {
			if (mouseX < Board.squares[0][i] + size) {
				return i;
			}
		}
		return -1;
	}

	private int mouseOnBoardY(int mouseY) {
		if (isPromoting) {
			if (turn == ID.Player1 && Board.squares[1][0] - Board.getSize() < mouseY && Board.squares[1][0] > mouseY) {
				return 1;
			} else if (turn == ID.Player2 && Board.squares[1][7] + Board.getSize() < mouseY
					&& Board.squares[1][7] + Board.getSize() * 2 > mouseY) {
				return 1;
			}
			return -1;
		}
		if (mouseY < Board.squares[1][0] || mouseY > Board.squares[1][7] + size) {
			return -1;
		}
		for (int i = 0; i < Board.squares[1].length; i++) {
			if (mouseY < Board.squares[1][i] + size) {
				return i;
			}
		}
		return -1;
	}

	private void switchTurns() {
		if (turn == ID.Player1) {
			turn = ID.Player2;
		} else {
			turn = ID.Player1;
		}
	}

	private void selectMove(int x, int y, ArrayList<String> possibleMoves) {
		if (possibleMoves.contains(Board.hash(x, y))) {
			int oldX = Pieces.selected[0].getX();
			int oldY = Pieces.selected[0].getY();
			Pieces.selected[0].move(x, y);
			Pieces eaten = Board.boardState.get(Board.hash(x, y));
			if (eaten != null && eaten.id != Pieces.selected[0].id) {
				handler.removeObject(eaten);
			}
			Pieces.selected[0].moveProccessing(oldX, oldY);
			if (ePawn != null) {
				ePawn.canE = false;
			}
			if (Pieces.selected[0] instanceof King) {

				if (Math.abs(x - oldX) >= 2) {
					King king = (King) Pieces.selected[0];
					oldX = king.castling.getX();
					oldY = king.castling.getY();
					if (x == 2) {
						king.castling.move(x + 1, y);
					} else {
						king.castling.move(x - 1, y);
					}
					Pieces.selected[0] = king.castling;
					king.castling.moveProccessing(oldX, oldY);

				}
			} else if (Pieces.selected[0] instanceof Pawn) {
				if (Math.abs(y - oldY) >= 2) {
					ePawn = (Pawn) Pieces.selected[0];
					ePawn.canE = true;
				} else if (turn == ID.Player1 && Board.boardState.get(Board.hash(x, y + 1)) instanceof Pawn
						&& Board.boardState.get(Board.hash(x, y + 1)).id != turn) {
					Board.boardState.replace(Board.hash(oldX, oldY), null);
					handler.removeObject(Board.boardState.get(Board.hash(x, y + 1)));

				} else if (turn == ID.Player2 && Board.boardState.get(Board.hash(x, y - 1)) instanceof Pawn
						&& Board.boardState.get(Board.hash(x, y - 1)).id != turn) {
					Board.boardState.replace(Board.hash(oldX, oldY), null);
					handler.removeObject(Board.boardState.get(Board.hash(x, y - 1)));
					
				}
				Pawn promotion = (Pawn) Pieces.selected[0];
				if (promotion.canPromote == true) {
					this.isPromoting = true;
				}

			}
			
			if (isPromoting == false) {
				Pieces.selected[0] = null;
				this.switchTurns();
			} 
		}

	}

}
