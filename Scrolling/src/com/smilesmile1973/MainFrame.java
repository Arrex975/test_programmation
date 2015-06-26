package com.smilesmile1973;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.smilesmile1973.graphics.BufferedImageUtils;
import com.smilesmile1973.graphics.DataBufferUtil;
import com.smilesmile1973.graphics.MetalFonts;
import com.smilesmile1973.graphics.PixelArray;
import com.smilesmile1973.graphics.SoundPcmUtils;
import com.smilesmile1973.micromod.Module;
import com.smilesmile1973.micromod.Player;
import com.smilesmile1973.micromod.PlayerListener;

public class MainFrame extends JFrame implements ClockListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7542561518577030491L;
	private GraphicsDevice device;
	private BufferStrategy bufferStrategy;
	private BufferedImage bufferedImage;
	private DataBufferUtil dataBufferUtil;
	private MetalFonts metalFonts;

	public DataBufferUtil getDataBufferUtil() {
		return dataBufferUtil;
	}

	public void setDataBufferUtil(DataBufferUtil dataBufferUtil) {
		this.dataBufferUtil = dataBufferUtil;
	}

	private int keyPressed;

	public int getKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(int keyPressed) {
		this.keyPressed = keyPressed;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	private PixelArray pixelArrayBackground;

	public PixelArray getPixelArrayBackground() {
		return pixelArrayBackground;
	}

	public void setPixelArrayBackground(PixelArray pixelArray) {
		this.pixelArrayBackground = pixelArray;
	}

	public MainFrame() throws Exception {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (device.isFullScreenSupported()) {
			setResizable(false);
			setIgnoreRepaint(true);
			setUndecorated(true);
			setResizable(false);
			setIgnoreRepaint(true);
			this.setBackground(Color.BLACK);
			device.setFullScreenWindow(this);
		} else {
			setResizable(false);
			setIgnoreRepaint(true);
			setUndecorated(true);
			setResizable(false);
			setIgnoreRepaint(true);
			this.setSize(800, 600);
			this.setVisible(true);
		}
		if (device.isDisplayChangeSupported()) {
			DisplayMode displayMode = new DisplayMode(800, 600, 32, 60);
			device.setDisplayMode(displayMode);
		}
		this.createBufferStrategy(2);
		bufferStrategy = this.getBufferStrategy();
		this.setBufferStrategy(bufferStrategy);
		validate();
		BufferedImage bufferedImage = BufferedImageUtils.loadBufferedImage("resources/london.jpg");
		PixelArray pixelArray = BufferedImageUtils.convertToPixelArray(bufferedImage, true);
		setDataBufferUtil(new DataBufferUtil(bufferedImage.getRaster().getDataBuffer(), bufferedImage.getWidth(), bufferedImage.getHeight()));
		metalFonts = new MetalFonts();
		setPixelArrayBackground(pixelArray);
		setBufferedImage(bufferedImage);
		Clock refreshScreenClock = new Clock(1000/50);
		Clock prepareClock = new Clock(1000/100);
		refreshScreenClock.addClockListener(this);
		prepareClock.addClockListener(new ClockListener() {
			@Override
			public void runMe() {
				switch (direction) {
				case 4:
					getPixelArrayBackground().scrollLeft(getScrollSpeed());
					break;
				case 6:
					getPixelArrayBackground().scrollRight(getScrollSpeed());
					break;
				case 8:
					getPixelArrayBackground().scrollUp(getScrollSpeed());
					break;
				case 2:
					getPixelArrayBackground().scrollDown(getScrollSpeed());
					break;
				}
			}
		});
		this.addKeyListener(this);
		//Music
		Module module = new Module( new java.io.FileInputStream("resources/xenophob.mod") );
		Player player = new Player( module, false, true );
		Thread thread = new Thread( player );
		player.addPlayerListener(new PlayerListener() {
			@Override
			public synchronized void  processFrequency(int[] data,int length) {
				int[][] rawPCM = SoundPcmUtils.transform(data,length);
				Complex[] fftComplex = SoundPcmUtils.getFFTfromRawPCM(SoundPcmUtils.lead0(rawPCM[0]));
				double[] amplitudes =  SoundPcmUtils.convertComplexToAmplitude(fftComplex, 48000);
				//SoundPcmUtils.displayTable(fftComplex, fftComplex.length,48000);
				leftSound = SoundPcmUtils.interpolate(60,rawPCM[0]);
				rightSound = SoundPcmUtils.interpolate(60,rawPCM[1]);
				leftSound = SoundPcmUtils.rescale(200, Short.MAX_VALUE, leftSound);
				rightSound = SoundPcmUtils.rescale(200, Short.MAX_VALUE, rightSound);
				
			}
		});
		thread.start();
	}
	int[] leftSound ;
	int[] rightSound;
	
	public void drawLines(Graphics g,int origineX,int origineY, int width,int[] points){
		if (points != null){
			int space = (int) Math.round((double)width / (double)points.length);
			for (int i = 0; i < points.length;i++){
				int x = origineX + space * i;
				int y2 = origineY + points[i];
				g.drawLine(x, origineY, x, y2);
			}
		}
	}
	
	public synchronized void drawLines2(Graphics g,int origineX,int origineY, int width,int[] points){
		g.drawLine(origineX,origineY,origineX+width,origineY);
		if (points != null){
			int space = (int) Math.round((double)width / (double)points.length);
			int x = origineX;
			int y = origineY;
			for (int i = 0; i < points.length;i++){
				int x2 = origineX + space * i;
				int y2 = origineY + points[i];
				g.drawLine(x, y, x2, y2);
				x = x2;
				y = y2;
			}
		}
	}

	private void setBufferStrategy(BufferStrategy bufferStrategy) {
		this.bufferStrategy = bufferStrategy;
	}
	
	int posX = 0;
	int posY = 10;
	boolean toLeft  = false;
	boolean toRight = true;
	boolean toDown  = true;
	boolean toUp   = false;
	private void render(Graphics g) {
		getDataBufferUtil().copyToDataBuffer(getPixelArrayBackground().getTable());
		metalFonts.write(posX, posY, "WELCOME ALEXANDRE", getDataBufferUtil());
		metalFonts.write(posX, posY + 45, "    (C)2015      ", getDataBufferUtil());
		metalFonts.write(posX, posY + 80, ".................", getDataBufferUtil());
		metalFonts.write(posX, posY + 115, "SCROLL SPEED " + String.valueOf(scrollSpeed), getDataBufferUtil());
		if (toRight){
			posX = posX + 4;
			if (posX > (800-576)){
				toRight = false;
				toLeft = true;
			}
		}
		if (toLeft){
			posX = posX - 4;
			if (posX < 10){
				toRight = true;
				toLeft = false;
			}
		}
		if (toDown){
			posY = posY + 4;
			if (posY > (600-150)){
				toDown = false;
				toUp = true;
			}
		}
		if (toUp){
			posY = posY - 4;
			if (posY < 10){
				toDown = true;
				toUp = false;
			}
		}
		g.drawImage(getBufferedImage(), 0, 0, null);
		g.setColor(Color.WHITE);
		drawLines2(g,10,300,300,leftSound);
		drawLines2(g,490,300,300,rightSound);
		bufferStrategy.show();
		g.dispose();

	}
	
	

	static public void main(String args[]) throws Exception {
		new MainFrame();
	}

	@Override
	public void runMe() {
		render(bufferStrategy.getDrawGraphics());
	}

	int direction = 0;
	int scrollSpeed = 1;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			direction = 4;
			break;
		case KeyEvent.VK_RIGHT:
			direction = 6;
			break;
		case KeyEvent.VK_UP:
			direction = 8;
			break;
		case KeyEvent.VK_DOWN:
			direction = 2;
			break;
		case KeyEvent.VK_ADD:
			incScrollSpeed();
			break;
		case KeyEvent.VK_SUBTRACT:
			decScrollSpeed();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	public void incScrollSpeed() {
		int maxSpeed = 200;
		if (scrollSpeed < maxSpeed) {
			scrollSpeed++;
		}
	}

	public int getScrollSpeed() {
		return scrollSpeed;
	}

	public void setScrollSpeed(int scrollSpeed) {
		this.scrollSpeed = scrollSpeed;
	}

	public void decScrollSpeed() {
		int minSpeed = 0;
		if (scrollSpeed > minSpeed) {
			scrollSpeed--;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}