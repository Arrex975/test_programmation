package com.smilesmile1973.graphics.fonts;

import java.util.TreeMap;

import com.smilesmile1973.graphics.IPixelArray;

public abstract class BitmapFont {

	private int width = 16;
	private int height = 16;

	private final TreeMap<String, int[]> fontsMap = new TreeMap<String, int[]>();

	private int posX;

	private int posY;

	private int interLine = 3;

	private String textToDisplay;

	public BitmapFont() {
		buildMap();
	}

	abstract protected void buildMap();

	protected TreeMap<String, int[]> getFontsMap() {
		return fontsMap;
	}

	public int getHeight() {
		return height;
	}

	public int getHeightOfText() {
		int result = 0;
		for (int i = 0; i < getTextToDisplay().length(); i++) {
			final char tmpChar = getTextToDisplay().charAt(i);
			if (tmpChar == '\n') {
				result = result + getHeight() + getInterLine();
			}
		}
		return result;
	}

	public int getInterLine() {
		return interLine;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public String getTextToDisplay() {
		return textToDisplay;
	}

	public int getWidth() {
		return width;
	}

	public void moveDown(int speed) {
		setPosXPosy(posX, posY + speed);
	}

	public void moveUp(int speed) {
		setPosXPosy(posX, posY - speed);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setInterLine(int interLine) {
		this.interLine = interLine;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosXPosy(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setTextToDisplay(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void write(IPixelArray out) {
		int x = posX;
		int y = posY;
		final int tmpX = x;
		int[] tmp = null;
		for (int i = 0; i < getTextToDisplay().length(); i++) {
			final char tmpChar = getTextToDisplay().charAt(i);
			if (tmpChar == '\n') {
				y = y + getHeight() + getInterLine();
				x = tmpX;
			} else {
				tmp = getFontsMap().get(String.valueOf(tmpChar));
				if (tmp != null) {
					out.fillRectangleOfPixel(x, y, getWidth(), getHeight(), tmp);
				} else {
					out.fillRectangleOfPixel(x, y, getWidth(), getHeight(), getFontsMap().get(String.valueOf(" ")));
				}
				x = x + getWidth();
			}
		}
	}

}
