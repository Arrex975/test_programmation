package com.smilesmile1973.graphics;

import java.util.TreeMap;

public class BitmapFont {

	protected final int width = 16;
	protected final int height = 23;
	private final TreeMap<String, int[]> fontsMap = new TreeMap<String, int[]>();
	private int posX;
	private int posY;
	private int interLine = 3;
	private String textToDisplay;

	protected TreeMap<String, int[]> getFontsMap() {
		return fontsMap;
	}

	public int getHeightOfText() {
		int result = 0;
		for (int i = 0; i < getTextToDisplay().length(); i++) {
			final char tmpChar = getTextToDisplay().charAt(i);
			if (tmpChar == '\n') {
				result = result + height + interLine;
			}
		}
		return result;
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

	public void moveDown(int speed) {
		setPosXPosy(posX, posY + speed);
	}

	public void moveUp(int speed) {
		setPosXPosy(posX, posY - speed);
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

	public void write(IPixelArray out) {
		int x = posX;
		int y = posY;
		final int tmpX = x;
		int[] tmp = null;
		for (int i = 0; i < getTextToDisplay().length(); i++) {
			final char tmpChar = getTextToDisplay().charAt(i);
			if (tmpChar == '\n') {
				y = y + height + interLine;
				x = tmpX;
			} else {
				tmp = getFontsMap().get(String.valueOf(tmpChar));
				if (tmp != null) {
					out.fillRectangleOfPixel(x, y, width, height, tmp);
				} else {
					out.fillRectangleOfPixel(x, y, width, height, getFontsMap().get(String.valueOf(" ")));
				}
				x = x + width;
			}
		}
	}

}
