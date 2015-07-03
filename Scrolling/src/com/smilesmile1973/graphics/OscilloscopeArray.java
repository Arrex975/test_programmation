package com.smilesmile1973.graphics;

public class OscilloscopeArray extends AbstractPixelArray {
	private int originX;
	private int originY;

	private int posX;

	private int posY;
	
	private int numberOfPoints = 300;
	
	private int space = 1;
	
	private int scaleFactor = 1;

	public OscilloscopeArray(int width, int height, int backgroundColor) {
		setTable(new int[width * height]);
		setWidth(width);
		setHeight(height);
		setBackgroundColor(backgroundColor);
		originY = getWidth() / 2;
		originX = 0;
		scaleFactor = (int) Math.round((double) Short.MAX_VALUE / ((double) getHeight()/2d));
		clear();
	}
	
	public void setNumberOfPoints(int numberOfPoints){
		this.numberOfPoints = numberOfPoints;
		space = (int) Math.round((double) getWidth() / (double) this.numberOfPoints);
	}

	public synchronized void drawOscillo(int[] points) {
		drawLine(originX, originY, originX + getWidth(), originY, 0xffffffff);
		if (points != null) {
			int x = originX;
			int y = originY;
			for (int i = 0; i < points.length; i++) {
				int x2 = originX + space * i;
				int y2 = originY + (points[i] / scaleFactor);
				drawLine(x, y, x2, y2, 0xffffffff);
				x = x2;
				y = y2;
			}
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void write(IPixelArray out) {
		out.fillRectangleOfPixel(getPosX(), getPosY(), getWidth(), getHeight(), getTable());
	}
}
