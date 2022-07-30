package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Driver extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -473349850293143017L;
	public static final int WIDTH =  1280, HEIGHT = 720;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Board board;
	
	public Driver() {
	handler = new Handler();
	board = new Board();
	this.addKeyListener(new KeyInput(handler));
	this.addMouseListener(new MouseInput(handler));
	
	Window window = new Window(WIDTH, HEIGHT, "Game", this);
	handler.addObject(new King(4,7, ID.Player1));
	handler.addObject(new Rook(0,7, ID.Player1));
	handler.addObject(new Rook(7,7, ID.Player1));
	handler.addObject(new Bishop(2,7, ID.Player1));
	handler.addObject(new Bishop(5,7, ID.Player1));
	handler.addObject(new Knight(1,7, ID.Player1));
	handler.addObject(new Knight(6,7, ID.Player1));
	handler.addObject(new Queen(3,7, ID.Player1));
	for(int i = 0; i < 8; i++) {
		handler.addObject(new Pawn(i, 6, ID.Player1));
	}
	handler.addObject(new King(4,0, ID.Player2));
	handler.addObject(new Rook(0,0, ID.Player2));
	handler.addObject(new Rook(7,0, ID.Player2));
	handler.addObject(new Bishop(2,0, ID.Player2));
	handler.addObject(new Bishop(5,0, ID.Player2));
	handler.addObject(new Knight(1,0, ID.Player2));
	handler.addObject(new Knight(6,0, ID.Player2));
	handler.addObject(new Queen(3,0, ID.Player2));
	for(int i = 0; i < 8; i++) {
		handler.addObject(new Pawn(i, 1, ID.Player2));
	}
	
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		board.render(g);
		handler.render(g);
		g.dispose();
		bs.show();
	}
	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			return var = max;
		}
		if(var <= min) {
			return var = min;
		} 
		return var;
	}
	
	public static void main(String[] args) {
		new Driver();

	}

}
