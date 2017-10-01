package com.smilesmile1973.graphics.oscillos;

import com.smilesmile1973.graphics.AbstractPixelArray;
import com.smilesmile1973.graphics.IPixelArray;

/**
 * This class contains the methods to draw an oscilloscope.
 *
 * @author Gérald Maréchal
 *
 */
public abstract class OscilloscopeArray extends AbstractPixelArray {

	private final int originX;

	private final int originY;

	private int posX;

	private int posY;

	/**
	 * The number of points bound by the lines. Less we have more it's faster.
	 */
	private int numberOfPoints = 30;

	private int space = 1;

	private int scaleFactor = 1;

	public OscilloscopeArray(int width, int height, int backgroundColor) {
		setTable(new int[width * height]);
		setWidth(width);
		setHeight(height);
		setBackgroundColor(backgroundColor);
		originY = getWidth() / 2;
		originX = 0;
		setScaleFactor((int) Math.round(Short.MAX_VALUE / (getHeight() / 2d)));
		clear();
	}

	abstract public void drawOscillo(int[] points);

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public int getOriginX() {
		return originX;
	}

	public int getOriginY() {
		return originY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getScaleFactor() {
		return scaleFactor;
	}

	public int getSpace() {
		return space;
	}

	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
		setSpace((int) Math.round((double) getWidth() / (double) this.numberOfPoints));
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setScaleFactor(int scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public void write(IPixelArray out) {
		out.fillRectangleOfPixel(getPosX(), getPosY(), getWidth(), getHeight(), getTable());
	}
}
