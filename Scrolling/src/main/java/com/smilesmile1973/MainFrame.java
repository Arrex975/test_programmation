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
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import com.smilesmile1973.graphics.BufferedImageUtils;
import com.smilesmile1973.graphics.DataBufferUtil;
import com.smilesmile1973.graphics.FFTarray;
import com.smilesmile1973.graphics.PixelArray;
import com.smilesmile1973.graphics.SoundPcmUtils;
import com.smilesmile1973.graphics.fonts.BitmapFont;
import com.smilesmile1973.graphics.fonts.NikNakFont;
import com.smilesmile1973.graphics.oscillos.OscilloScopePoint;
import com.smilesmile1973.graphics.oscillos.OscilloscopeArray;
import com.smilesmile1973.micromod.Module;
import com.smilesmile1973.micromod.Player;
import com.smilesmile1973.micromod.PlayerListener;

public class MainFrame extends JFrame implements Runnable, KeyListener {
	/**
	 *
	 */
	private static final long serialVersionUID = -7542561518577030491L;

	static public void main(String args[]) throws Exception {
		new MainFrame();
	}

	double[] amplitudes;
	private BufferedImage bufferedImage;
	long cycleTime;
	private DataBufferUtil dataBufferUtil;
	private final GraphicsDevice device;
	private int direction = 0;
	boolean drawAlready = false;
	private final FFTarray fftArrayLeft;
	private final FFTarray fftArrayRight;
	private final int heightText;
	private int keyPressed;
	private int[] leftSound;
	private int lengthSound;
	private final BitmapFont fonts;
	private final OscilloscopeArray oscilloLeft;
	private final OscilloscopeArray oscilloRight;
	private final PixelArray pixelArrayBackground;
	Player player;
	private int[] rightSound;
	private int scrollSpeed = 1;

	private int[] soundData;

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
			final DisplayMode displayMode = new DisplayMode(800, 600, 32, 60);
			device.setDisplayMode(displayMode);
		}

		this.createBufferStrategy(2);
		validate();
		final BufferedImage bufferedImage = BufferedImageUtils.loadBufferedImage("/london.jpg");
		final String content = new Scanner(MainFrame.class.getResourceAsStream("/cvNikNak.txt"), "UTF-8")
				.useDelimiter("\\A").next();
		pixelArrayBackground = BufferedImageUtils.convertToPixelArray(bufferedImage, true);
		setDataBufferUtil(new DataBufferUtil(bufferedImage.getRaster().getDataBuffer(), bufferedImage.getWidth(),
				bufferedImage.getHeight(), 0xFF00FF00));
		fonts = new NikNakFont();
		fonts.setTextToDisplay(content);
		heightText = fonts.getHeightOfText();
		fftArrayLeft = new FFTarray();
		fftArrayRight = new FFTarray();
		fftArrayLeft.setPosX(10);
		fftArrayLeft.setPosY(400);
		fftArrayRight.setPosX(490);
		fftArrayRight.setPosY(400);

		oscilloLeft = new OscilloScopePoint(300, 400, 0);
		oscilloLeft.setPosX(10);
		oscilloLeft.setPosY(100);

		oscilloRight = new OscilloScopePoint(300, 400, 0);
		oscilloRight.setPosX(490);
		oscilloRight.setPosY(100);

		setBufferedImage(bufferedImage);
		// Main Loop
		Thread loop = new Thread(this);
		loop.start();
		this.addKeyListener(this);
		// Music
		buildThreadMusic().start();

	}

	private Thread buildThreadMusic() throws IOException {
		final Module module = new Module(MainFrame.class.getResourceAsStream("/big_beard.mod"));
		player = new Player(module, false, true);
		player.addPlayerListener(new PlayerListener() {
			@Override
			public synchronized void processFrequency(int[] data, int length) {
				soundData = data;
				lengthSound = length;
			}
		});
		return new Thread(player);
	}

	public void decScrollSpeed() {
		final int minSpeed = 0;
		if (scrollSpeed > minSpeed) {
			scrollSpeed--;
		}
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public DataBufferUtil getDataBufferUtil() {
		return dataBufferUtil;
	}

	public int getKeyPressed() {
		return keyPressed;
	}

	public int getScrollSpeed() {
		return scrollSpeed;
	}

	public void incScrollSpeed() {
		final int maxSpeed = 200;
		if (scrollSpeed < maxSpeed) {
			scrollSpeed++;
		}
	}

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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private synchronized void render(Graphics g) {
		// The display is here
		g.drawImage(getBufferedImage(), 0, 0, null);
		getBufferStrategy().show();
		g.dispose();
	}

	@Override
	public void run() {
		cycleTime = System.currentTimeMillis();
		BufferStrategy strategy = getBufferStrategy();
		while (true) {
			updateGui(strategy);
			synchFramerate();
		}

	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public void setDataBufferUtil(DataBufferUtil dataBufferUtil) {
		this.dataBufferUtil = dataBufferUtil;
	}

	public void setKeyPressed(int keyPressed) {
		this.keyPressed = keyPressed;
	}

	public void setScrollSpeed(int scrollSpeed) {
		this.scrollSpeed = scrollSpeed;
	}

	private void synchFramerate() {
		cycleTime = cycleTime + 20;
		long difference = cycleTime - System.currentTimeMillis();
		try {
			Thread.sleep(Math.max(0, difference));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// @Override
	public void updateGui(BufferStrategy bufferStrategy) {
		switch (direction) {
		case 4:
			pixelArrayBackground.scrollLeft(getScrollSpeed());
			break;
		case 6:
			pixelArrayBackground.scrollRight(getScrollSpeed());
			break;
		case 8:
			pixelArrayBackground.scrollUp(getScrollSpeed());
			break;
		case 2:
			pixelArrayBackground.scrollDown(getScrollSpeed());
			fonts.moveUp(2);
			if (fonts.getPosY() <= -heightText) {
				fonts.setPosY(pixelArrayBackground.getWidth());
			}
			break;
		}
		if (soundData != null) {
			final int[][] rawPCM = SoundPcmUtils.transform(soundData, lengthSound);
			leftSound = SoundPcmUtils.interpolate(oscilloLeft.getNumberOfPoints(), rawPCM[0]);
			rightSound = SoundPcmUtils.interpolate(oscilloLeft.getNumberOfPoints(), rawPCM[1]);
			oscilloLeft.drawOscillo(leftSound);
			oscilloRight.drawOscillo(rightSound);
			fftArrayLeft.processSoundData(rawPCM[0], lengthSound);
			fftArrayRight.processSoundData(rawPCM[1], lengthSound);
		}
		fftArrayLeft.write(getDataBufferUtil());
		fftArrayRight.write(getDataBufferUtil());
		oscilloLeft.write(getDataBufferUtil());
		oscilloRight.write(getDataBufferUtil());
		fonts.write(getDataBufferUtil());
		fftArrayLeft.clear();
		fftArrayRight.clear();
		oscilloLeft.clear();
		oscilloRight.clear();
		render(bufferStrategy.getDrawGraphics());
		getDataBufferUtil().copyToDataBuffer(pixelArrayBackground.getTable());
	}
}