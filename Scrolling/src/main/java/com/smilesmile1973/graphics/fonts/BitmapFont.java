package com.smilesmile1973.graphics.fonts;

import java.util.TreeMap;

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

	public TreeMap<String, int[]> getFontsMap() {
		return fontsMap;
	}

	public int getHeight() {
		return height;
	}

	public int getHeightOfText() {
		int result = 0;
		if (getTextToDisplay() != null && getTextToDisplay().length() > 0) {
			result = getHeight();
			for (int i = 0; i < getTextToDisplay().length(); i++) {
				final char tmpChar = getTextToDisplay().charAt(i);
				if (tmpChar == '\n') {
					result = result + getHeight() + getInterLine();
				}
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
}
